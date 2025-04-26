package com.poli.reciclApp.model;

import com.poli.reciclApp.model.enums.Rol;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String telefono;
    private String direccion;
    private Localidad localidad;
    private Rol rol;

    public Usuario(String id, String nombre, String correo, String contrasena, String telefono, String direccion, Localidad localidad, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
        this.localidad = localidad;
        this.rol = rol;
    }

    public Usuario() {
        
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public Localidad getLocalidad() { return localidad; }
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

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}