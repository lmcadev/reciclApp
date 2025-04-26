package com.poli.reciclApp.model;

import com.poli.reciclApp.model.enums.TipoNotificacion;
import com.poli.reciclApp.model.enums.EstadoSesion;
import java.time.LocalDateTime;

public class Notificacion {
    private String id;
    private Usuario usuario;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private TipoNotificacion tipo;
    private EstadoSesion estado;

    public void enviarWhatsApp() {
        // Lógica para enviar mensaje por WhatsApp (simulado)
    }

    public void marcarComoLeida() {
        estado = EstadoSesion.CERRADA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public TipoNotificacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoNotificacion tipo) {
        this.tipo = tipo;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
    }
}