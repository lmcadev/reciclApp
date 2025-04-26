package com.poli.reciclApp.model;

import com.poli.reciclApp.dao.UsuarioDAO;
import com.poli.reciclApp.model.enums.EstadoSesion;

public class Login {
    private Usuario usuario;
    private String token;
    private EstadoSesion estado;

    public boolean iniciarSesion(String correo, String contrasena) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario encontrado = usuarioDAO.buscarPorCorreo(correo);


        System.out.println("Buscando correo: " + correo);



if (encontrado != null) {
    System.out.println("Encontrado: " + encontrado.getCorreo() + " / " + encontrado.getContrasena());
}

        if (encontrado != null && encontrado.getContrasena().equals(contrasena)) {
            this.usuario = encontrado;
            this.estado = EstadoSesion.ACTIVA;
            this.token = "fake-jwt-token"; 
            return true;
        }
        return false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void cerrarSesion() {
        this.estado = EstadoSesion.CERRADA;
    }

    public boolean esValida() {
        return this.estado == EstadoSesion.ACTIVA;
    }
}
