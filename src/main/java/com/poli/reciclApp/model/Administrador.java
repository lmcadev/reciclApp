package com.poli.reciclApp.model;
import java.util.List;

import org.springframework.ui.Model;

import com.poli.reciclApp.dao.LocalidadDAO;
import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.dao.UsuarioDAO;
import com.poli.reciclApp.model.enums.Rol;


public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String correo, String contrasena, String telefono, String direccion, String localidad, int puntos ) {
        super(id, nombre, correo, contrasena, telefono, direccion, localidad, Rol.ADMINISTRADOR, puntos);
    }

    public String asignarEmpresaARecoleccion(Model model) {
        List<Recoleccion> pendientes = new RecoleccionDAO().listarSinEmpresa();
        List<Usuario> empresas = new UsuarioDAO().listarPorRol(Rol.EMPRESA_RECOLECTORA);
        List<Localidad> localidades = new LocalidadDAO().listarTodas();
        model.addAttribute("pendientes", pendientes);
        model.addAttribute("empresas", empresas);
        model.addAttribute("localidades", localidades);
        
        return "admin/asignarRecoleccion";
    }

    public String asignarRol(Model model) {
        List<Usuario> usuarios = new UsuarioDAO().listarTodos();
        model.addAttribute("usuarios", usuarios);
        List<Localidad> localidades = new LocalidadDAO().listarTodas();
        model.addAttribute("localidades", localidades);
        return "admin/asignarRol";
    }

    public String generarReporte(Model model) {
        List<Recoleccion> reportes = new RecoleccionDAO().obtenerTodas();
        model.addAttribute("reportes", reportes);
        return "admin/reporte";
    }
}