package com.poli.reciclApp.model;

import java.util.List;

import org.springframework.ui.Model;

import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.model.enums.Rol;
import com.poli.reciclApp.model.enums.TipoResiduo;

import jakarta.servlet.http.HttpSession;

public class Empresa extends Usuario {
    private TipoResiduo especialidad;


    public Empresa(String id, String nombre, String correo, String contrasena, String telefono, String direccion, String localidad, TipoResiduo especialidad, int puntos) {
        super(id, nombre, correo, contrasena, telefono, direccion, localidad, Rol.EMPRESA_RECOLECTORA, puntos);
        this.especialidad = especialidad;
    }

    public TipoResiduo getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(TipoResiduo especialidad) {
        this.especialidad = especialidad;
    }

    public String registrarPeso(HttpSession session, Model model) {
        Usuario empresa = (Usuario) session.getAttribute("usuario");
        List<Recoleccion> pendientes = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
        model.addAttribute("pendientes", pendientes);
        return "empresa/registrarPeso";
    }

    public void confirmarRecoleccion(Recoleccion r) {
        r.marcarComoRealizada();
    }

    

    public void consultarHistorial() {
        // Consultar historial simulado
    }
}
