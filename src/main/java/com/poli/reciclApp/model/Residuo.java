package com.poli.reciclApp.model;

import com.poli.reciclApp.model.enums.TipoResiduo;
import java.time.LocalDateTime;

public class Residuo {
    private String id;
    private TipoResiduo tipo;
    private float peso;
    private LocalDateTime fechaRegistro;

    public int calcularPuntos() {
        switch (tipo) {
            case ORGANICO: return 5;
            case INORGANICO: return 10;
            case PELIGROSO: return 20;
            default: return 0;
        }
    }

    public boolean esPeligroso() {
        return tipo == TipoResiduo.PELIGROSO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoResiduo getTipo() {
        return tipo;
    }

    public void setTipo(TipoResiduo tipo) {
        this.tipo = tipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}