package com.poli.reciclApp.model;
import com.poli.reciclApp.model.enums.Rol;


public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String correo, String contrasena, String telefono, String direccion, String localidad) {
        super(id, nombre, correo, contrasena, telefono, direccion, localidad, Rol.ADMINISTRADOR);
    }

    public void asignarEmpresaARecoleccion() {
        // Simulación de asignación
    }

    public void asignarRol(Usuario usuario, Rol nuevoRol) {
        // Asignación de rol (se modificaría en BD)
    }

    public void generarReporte() {
        // Lógica para generar reporte global
    }
}