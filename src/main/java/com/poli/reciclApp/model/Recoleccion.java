package com.poli.reciclApp.model;

import com.poli.reciclApp.model.enums.Frecuencia;
import com.poli.reciclApp.model.enums.EstadoRecoleccion;
import java.time.LocalDateTime;

public class Recoleccion {
    private String id;
    private Usuario usuario;
    private Usuario empresa;
    private Residuo residuo;
    private LocalDateTime fechaProgramada;
    private LocalDateTime fechaRecoleccion;
    private String turno;
    private Frecuencia frecuencia;
    private EstadoRecoleccion estado;
    private int puntos;

    public void marcarComoRealizada() {
        this.estado = EstadoRecoleccion.REALIZADA;
    }

    public void cancelar() {
        this.estado = EstadoRecoleccion.CANCELADA;
    }

    public boolean esReprogramable() {
        return estado == EstadoRecoleccion.PROGRAMADA;
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



    public Residuo getResiduo() {
        return residuo;
    }

    public void setResiduo(Residuo residuo) {
        this.residuo = residuo;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public LocalDateTime getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public void setFechaRecoleccion(LocalDateTime fechaRecoleccion) {
        this.fechaRecoleccion = fechaRecoleccion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Frecuencia getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    public EstadoRecoleccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecoleccion estado) {
        this.estado = estado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setEmpresa(Usuario empresa) {
        this.empresa = empresa;
    }

public Usuario getEmpresa() {
    return empresa;
}

}