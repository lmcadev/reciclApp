package com.poli.reciclApp.controller;

import com.poli.reciclApp.dao.LocalidadDAO;
import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.dao.UsuarioDAO;
import com.poli.reciclApp.model.Administrador;
import com.poli.reciclApp.model.Localidad;
import com.poli.reciclApp.model.Recoleccion;
import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.enums.Rol;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard"; 
    }

    @GetMapping("/asignar")
    public String asignar(Model model) {

        try {
            Administrador administrador = new Administrador(null, null, null, null, null, null, null, 0);
            return administrador.asignarEmpresaARecoleccion(model);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error"; // Return a default view or error page
        }
    }

    @GetMapping("/roles")
    public String roles(Model model) {
       try {
        Administrador administrador = new Administrador(null, null, null, null, null, null, null, 0);
        return administrador.asignarRol(model);
       } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return "error"; // Return a default view or error page
       }
    }

    @GetMapping("/reporte")
    public String reporte(Model model) {
        try {
            Administrador administrador = new Administrador(null, null, null, null, null, null, null, 0);
            return administrador.generarReporte(model);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error"; // Return a default view or error page
        }
    }

    @PostMapping("/asignarRecoleccion")
    public String asignarRecoleccion(@RequestParam("idRecoleccion") String recoId,
            @RequestParam("empresaId") String empresaId) {
        new RecoleccionDAO().asignarEmpresa(recoId, empresaId);
        return "redirect:/admin/asignar"; 
    }

    @PostMapping("/asignarRol")
    public String asignarRol(@RequestParam("usuarioId") String usuarioId,
            @RequestParam("rol") Rol nuevoRol) {
        new UsuarioDAO().actualizarRol(usuarioId, nuevoRol);
        return "redirect:/admin/roles"; 
    }

    // Agregar Usuario
    @PostMapping("/agregarUsuario")
    public String agregarUsuario(@RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("telefono") String telefono,
            @RequestParam("direccion") String direccion,
            @RequestParam("localidad_id") String localidad_id,
            @RequestParam("rol") Rol rol) {
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setContrasena(contrasena);
        nuevo.setTelefono(telefono);
        nuevo.setDireccion(direccion);
        nuevo.setLocalidad(localidad_id);
        nuevo.setRol(rol);
        try {
            new UsuarioDAO().registrar(nuevo);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/admin/roles";
    }

    // Editar Usuario
    @PostMapping("/editarUsuario")
    public String editarUsuario(@RequestParam("usuarioId") String id,
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("direccion") String direccion,
            @RequestParam("localidad_id") String localidad_id,
            @RequestParam("rol") Rol rol) {
        new UsuarioDAO().editarUsuario(id, nombre, correo, telefono, direccion, localidad_id, rol);
        return "redirect:/admin/roles";
    }

    // Eliminar Usuario
    @PostMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestParam("usuarioId") String id) {
        new UsuarioDAO().eliminar(id);
        return "redirect:/admin/roles";
    }
}
