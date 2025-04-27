package com.poli.reciclApp.model;

import com.poli.reciclApp.model.enums.Rol;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String telefono;
    private String direccion;
    private String localidad;
    private Rol rol;
    private int puntos;

    public Usuario(String id, String nombre, String correo, String contrasena, String telefono, String direccion, String localidad, Rol rol, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
        this.localidad = localidad;
        this.rol = rol;
        this.puntos = puntos;
    }

    public Usuario() {
        
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getLocalidad() { return localidad; }
    public Rol getRol() { return rol; }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getPuntos() {  
        return puntos;
    }
    
    public void setPuntos(int puntos) {  
        this.puntos = puntos;
    }
}