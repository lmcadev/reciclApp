package com.poli.reciclApp.model;
import com.poli.reciclApp.model.enums.Rol;


public class EmpresaRecolectora extends Usuario {
    public EmpresaRecolectora(String id, String nombre, String correo, String contrasena, String telefono, String direccion, String localidad) {
        super(id, nombre, correo, contrasena, telefono, direccion, localidad, Rol.EMPRESA_RECOLECTORA);
    }

    public void confirmarRecoleccion(Recoleccion r) {
        r.marcarComoRealizada();
    }

    public void registrarPeso(Residuo residuo, float peso) {
        //TODO: Implementar lógica para registrar el peso del residuo
        residuo = new Residuo();
    }

    public void consultarHistorial() {
        // Consultar historial simulado
    }
}