package com.poli.reciclApp.model;

import java.time.LocalDateTime;

import org.springframework.ui.Model;

import com.poli.reciclApp.dao.NotificacionDAO;
import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.dao.ResiduoDAO;
import com.poli.reciclApp.model.enums.EstadoRecoleccion;
import com.poli.reciclApp.model.enums.EstadoSesion;
import com.poli.reciclApp.model.enums.Frecuencia;
import com.poli.reciclApp.model.enums.Rol;
import com.poli.reciclApp.model.enums.TipoNotificacion;
import com.poli.reciclApp.model.enums.TipoResiduo;
import com.poli.reciclApp.util.UUIDGenerator;

import jakarta.servlet.http.HttpSession;

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

    public Usuario(String id, String nombre, String correo, String contrasena, String telefono, String direccion,
            String localidad, Rol rol, int puntos) {
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

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public Rol getRol() {
        return rol;
    }

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

    /**
     * Solicita la recolección de un residuo, registra la información del residuo, 
     * la recolección programada y envía una notificación al usuario.
     *
     * @param tipoResiduo El tipo de residuo a recolectar (debe coincidir con los valores de TipoResiduo).
     * @param peso El peso del residuo en kilogramos.
     * @param fechaHora La fecha y hora programada para la recolección en formato ISO-8601 (yyyy-MM-ddTHH:mm).
     * @param session La sesión HTTP actual que contiene la información del usuario.
     * @return Una cadena que redirige al historial del usuario después de registrar la solicitud.
     * 
     * @throws IllegalArgumentException Si el tipo de residuo no coincide con los valores de TipoResiduo.
     * @throws DateTimeParseException Si el formato de fechaHora no es válido.
     */
    public String solicitarRecoleccion(String tipoResiduo, float peso, String fechaHora, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Residuo residuo = new Residuo();
        residuo.setId(UUIDGenerator.generar());
        residuo.setTipo(TipoResiduo.valueOf(tipoResiduo));
        residuo.setPeso(peso);
        residuo.setFechaRegistro(LocalDateTime.now());
        new ResiduoDAO().registrar(residuo);

        Recoleccion recoleccion = new Recoleccion();
        recoleccion.setId(UUIDGenerator.generar());
        recoleccion.setUsuario(usuario);
        recoleccion.setResiduo(residuo);
        recoleccion.setFechaProgramada(LocalDateTime.parse(fechaHora));
        recoleccion.setTurno("Mañana");
        recoleccion.setFrecuencia(Frecuencia.BAJO_DEMANDA);
        recoleccion.setEstado(EstadoRecoleccion.PROGRAMADA);
        recoleccion.setPuntos(residuo.calcularPuntos());
        new RecoleccionDAO().registrar(recoleccion);

        Notificacion notificacion = new Notificacion();
        notificacion.setId(UUIDGenerator.generar());
        notificacion.setUsuario(usuario);
        notificacion.setMensaje("Tu solicitud de recolección fue registrada correctamente.");
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setTipo(TipoNotificacion.CONFIRMACION);
        notificacion.setEstado(EstadoSesion.ACTIVA);
        new NotificacionDAO().registrar(notificacion);

        return "redirect:/usuario/historial";
    }

    /**
     * Recupera el historial de recolecciones asociado al usuario que ha iniciado sesión y lo agrega al modelo.
     * 
     * @param session la sesión HTTP que contiene los atributos de la sesión del usuario
     * @param model   el modelo al que se añadirá el historial de recolecciones del usuario
     * @return el nombre de la vista que se renderizará, específicamente "usuario/historial"
     * 
     * Este método verifica si hay un usuario almacenado en la sesión. Si se encuentra un usuario,
     * recupera su historial de recolecciones utilizando el RecoleccionDAO y lo agrega al modelo
     * bajo el atributo "historial". Si no se encuentra un usuario en la sesión, el método simplemente
     * devuelve el nombre de la vista sin modificar el modelo.
     */
    public String consultarHistorial(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("historial", new RecoleccionDAO().obtenerPorUsuario(usuario.getId()));
        }
        return "usuario/historial";

    }

    public String canjearPuntos(HttpSession session, Model model) {
        return null;
    }

    public String recibirNotificaciones(HttpSession session, Model model) {

        return null;
    }

    /**
     * Recupera los puntos del usuario actualmente autenticado y los agrega al modelo.
     *
     * @param session la sesión HTTP actual, utilizada para recuperar al usuario autenticado
     * @param model   el modelo al que se añadirá la información del usuario y sus puntos
     * @return el nombre de la vista que se renderizará, específicamente "usuario/puntos"
     *
     * Si se encuentra un usuario en la sesión, su información y puntos se añaden al modelo.
     * De lo contrario, el modelo permanece sin cambios.
     */
    public String verPuntos(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("puntos", usuario.getPuntos());
        }
        return "usuario/puntos";
    }
}