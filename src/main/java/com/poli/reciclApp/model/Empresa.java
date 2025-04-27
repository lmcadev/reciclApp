package com.poli.reciclApp.model;

import java.util.List;

import org.springframework.ui.Model;

import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.model.enums.Rol;
import com.poli.reciclApp.model.enums.TipoResiduo;

import jakarta.servlet.http.HttpSession;

/**
 * La clase Empresa representa una entidad que extiende la funcionalidad de un Usuario
 * y está especializada en la gestión de residuos. Incluye atributos y métodos específicos
 * para manejar las operaciones relacionadas con la recolección de residuos.
 * 
 * Atributos:
 * - especialidad: Define el tipo de residuo en el que la empresa está especializada.
 * 
 * Métodos:
 * - registrarPeso(HttpSession session, Model model): Maneja el registro de peso para una empresa,
 *   recuperando las recolecciones pendientes asociadas con la empresa y pasándolas al modelo
 *   para su visualización en la vista correspondiente.
 * 
 * - confirmarRecoleccion(Recoleccion r): Marca una recolección como realizada.
 * 
 * - consultarHistorial(HttpSession session, Model model): Recupera el historial de recolección
 *   para la empresa que ha iniciado sesión y lo pasa al modelo para su representación en la vista.
 * 
 * Esta clase está diseñada para ser utilizada en el contexto de una aplicación web que gestiona
 * la recolección de residuos, proporcionando funcionalidades específicas para las empresas recolectoras.
 */
public class Empresa extends Usuario {
    private TipoResiduo especialidad;


    public Empresa(String id, String nombre, String correo, String contrasena, String telefono, String direccion, String localidad, TipoResiduo especialidad, int puntos) {
        super(id, nombre, correo, contrasena, telefono, direccion, localidad, Rol.EMPRESA_RECOLECTORA, puntos);
        this.especialidad = especialidad;
    }

    public TipoResiduo getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(TipoResiduo especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Maneja el registro de peso para una empresa.
     * 
     * Este método recupera la empresa que ha iniciado sesión desde la sesión HTTP y
     * obtiene una lista de recolecciones pendientes asociadas con la empresa. Las
     * recolecciones pendientes se agregan al modelo para ser mostradas en la vista
     * "registrarPeso".
     * 
     * @param session la sesión HTTP que contiene la información del usuario que ha iniciado sesión.
     * @param model   el objeto modelo utilizado para pasar atributos a la vista.
     * @return el nombre de la vista a renderizar, específicamente "empresa/registrarPeso".
     */
    public String registrarPeso(HttpSession session, Model model) {
        Usuario empresa = (Usuario) session.getAttribute("usuario");
        List<Recoleccion> pendientes = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
        model.addAttribute("pendientes", pendientes);
        return "empresa/registrarPeso";
    }

    /**
     * Confirma la recolección marcándola como realizada.
     *
     * @param r el objeto de recolección que se marcará como realizado
     */
    public void confirmarRecoleccion(Recoleccion r) {
        r.marcarComoRealizada();
    }

    

    /**
     * Maneja la recuperación del historial de recolección para una empresa específica.
     * 
     * Este método obtiene la empresa que ha iniciado sesión desde la sesión HTTP y la utiliza
     * para recuperar el historial de recolección asociado con la empresa. El historial
     * y los detalles de la empresa se agregan al modelo para su representación en la vista.
     * 
     * @param session La sesión HTTP que contiene la información del usuario que ha iniciado sesión.
     * @param model   El objeto modelo utilizado para pasar atributos a la vista.
     * @return Una cadena que representa el nombre de la vista a renderizar, en este
     *         caso, "empresa/historial".
     */
    public String consultarHistorial(HttpSession session, Model model) {
        Empresa empresa = (Empresa) session.getAttribute("usuario");

        if (empresa != null) {
            List<Recoleccion> historial = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
            model.addAttribute("historial", historial);
            model.addAttribute("empresa", empresa);
        }
    
        return "empresa/historial";
    }
}
