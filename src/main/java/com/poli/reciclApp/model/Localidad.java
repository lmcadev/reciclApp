package com.poli.reciclApp.model;

public class Localidad {
    private String id;
    private String nombre;
    private String diaRecoleccionOrganico;
    
    public Localidad(String string, String string2) {
        
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDiaRecoleccionOrganico() {
        return diaRecoleccionOrganico;
    }
    public void setDiaRecoleccionOrganico(String diaRecoleccionOrganico) {
        this.diaRecoleccionOrganico = diaRecoleccionOrganico;
    }
    public Localidad(String id, String nombre, String diaRecoleccionOrganico) {
        this.id = id;
        this.nombre = nombre;
        this.diaRecoleccionOrganico = diaRecoleccionOrganico;
    }
    public Localidad() {
        //TODO Auto-generated constructor stub
    }
}

