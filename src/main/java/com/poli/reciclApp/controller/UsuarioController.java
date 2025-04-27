package com.poli.reciclApp.controller;


import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.enums.TipoResiduo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * Controlador para manejar las solicitudes relacionadas con los usuarios.
 * 
 * Este controlador proporciona varios endpoints para gestionar las acciones
 * del usuario.
 * 
 * Los métodos de este controlador interactúan con la sesión HTTP para recuperar
 * la información del usuario conectado y utilizan el modelo para pasar datos a
 * las vistas correspondientes.
 * 
 * Endpoints manejados:
 * - GET /usuario/historial: Muestra el historial del usuario.
 * - GET /usuario/dashboard: Muestra el dashboard del usuario.
 * - GET /usuario/puntos: Muestra los puntos acumulados por el usuario.
 * - GET /usuario/solicitarRecoleccion: Muestra la página para solicitar una recolección.
 * - POST /usuario/solicitarRecoleccion: Procesa la solicitud de recolección.
 * - POST /usuario/cancelarRecoleccion: Cancela una solicitud de recolección.
 * - POST /usuario/editarRecoleccion: Edita la fecha de una recolección existente.
 * 
 * Este controlador utiliza las siguientes clases:
 * - Usuario: Representa al usuario conectado.
 * - TipoResiduo: Enumera los tipos de residuos disponibles.
 * - RecoleccionDAO: Proporciona acceso a los datos relacionados con las recolecciones.
 * 
 * Las vistas correspondientes se encuentran en la carpeta "usuario" y están
 * organizadas según las acciones realizadas por el usuario.
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    /**
     * Maneja las solicitudes GET al endpoint "/dashboard".
     * Recupera el usuario actual de la sesión HTTP y lo agrega al modelo
     * si el usuario ha iniciado sesión. Devuelve el nombre de la vista para el dashboard del usuario.
     *
     * @param session la sesión HTTP que contiene la información del usuario
     * @param model   el modelo para pasar atributos a la vista
     * @return el nombre de la vista para el dashboard del usuario ("usuario/dashboard")
     */
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }
        return "usuario/dashboard";
    }
    
    /**
     * Maneja la solicitud GET para mostrar la página del historial del usuario.
     * 
     * Este método recupera la sesión actual del usuario y verifica si hay un usuario 
     * conectado. Si se encuentra un usuario en la sesión, invoca el método para 
     * consultar el historial del usuario y llena el modelo con los datos relevantes.
     * 
     * @param session el objeto de sesión HTTP utilizado para recuperar el usuario conectado
     * @param model el objeto modelo utilizado para pasar datos a la vista
     */
    @GetMapping("/historial")
    public void verHistorial(HttpSession session, Model model) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            usuario.consultarHistorial(session, model);
        }
    }

    
    /**
     * Maneja la solicitud HTTP GET para el endpoint "/puntos".
     * Este método recupera la sesión del usuario actual y muestra sus puntos.
     *
     * @param session la sesión HTTP actual, utilizada para recuperar el usuario conectado.
     * @param model   el objeto modelo utilizado para pasar atributos a la vista.
     */
    @GetMapping("/puntos")
public void puntos(HttpSession session, Model model) {
    
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        usuario.verPuntos(session, model);
    }
    
}


    /**
     * Maneja la solicitud GET para el endpoint "solicitarRecoleccion".
     * Este método se utiliza para mostrar la página donde un usuario puede solicitar una recolección de residuos.
     * 
     * @param session la sesión HTTP actual, utilizada para recuperar el usuario conectado.
     * @param model   el objeto modelo utilizado para pasar atributos a la vista.
     * 
     * El método verifica si un usuario ha iniciado sesión recuperando el atributo "usuario" de la sesión.
     * Si el usuario está presente, agrega la lista de tipos de residuos (TipoResiduo.values())
     * y el objeto usuario al modelo. Estos atributos se utilizan en la vista para mostrar información relevante.
     */
    @GetMapping("/solicitarRecoleccion")
    public String solicitarRecoleccion(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("tiposResiduo", TipoResiduo.values());
            model.addAttribute("usuario", usuario);
        }
        return "usuario/solicitarRecoleccion";
    }

    /**
     * Maneja la solicitud para programar una recolección de residuos.
     *
     * @param tipoResiduo    El tipo de residuo a recolectar, proporcionado como una cadena.
     * @param peso           El peso del residuo a recolectar, proporcionado como un número flotante.
     * @param fechaHora      La fecha y hora programada para la recolección, proporcionada como una cadena.
     * @param session        El objeto de sesión HTTP utilizado para recuperar la información del usuario actual.
     */
    @PostMapping("/solicitarRecoleccion")
    public void solicitarRecoleccion(
            @RequestParam("tipoResiduo") String tipoResiduo,
            @RequestParam("peso") float peso,
            @RequestParam("fechaProgramada") String fechaHora,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        usuario.solicitarRecoleccion(tipoResiduo, peso, fechaHora, session);

    }

    
    /**
     * Maneja la cancelación de una solicitud de recolección.
     *
     * Este método está mapeado a la solicitud POST para el endpoint "/cancelarRecoleccion".
     * Recibe el ID de la recolección a cancelar como un parámetro de la solicitud,
     * invoca el método correspondiente del DAO para cancelar la recolección y luego
     * redirige al usuario a la página de "historial".
     *
     * @param id El ID de la recolección a cancelar, proporcionado como un parámetro de la solicitud.
     * @return Una cadena de redirección a la página de "historial".
     */
    @PostMapping("/cancelarRecoleccion")
    public String cancelarRecoleccion(@RequestParam("idRecoleccion") String id) {
        new RecoleccionDAO().cancelarRecoleccion(id);
        return "redirect:/usuario/historial";
    }

    
    /**
     * Maneja la solicitud POST para editar la fecha de recolección de una recolección específica.
     *
     * @param id El ID de la recolección que se desea actualizar.
     * @param nuevaFecha La nueva fecha para la recolección en formato ISO-8601 (por ejemplo, "yyyy-MM-ddTHH:mm:ss").
     * @return Una cadena de redirección a la página del historial de recolecciones del usuario.
     */
    @PostMapping("/editarRecoleccion")
    public String editarRecoleccion(@RequestParam("idRecoleccion") String id,
            @RequestParam("nuevaFecha") String nuevaFecha) {
        new RecoleccionDAO().editarFechaRecoleccion(id, LocalDateTime.parse(nuevaFecha));
        return "redirect:/usuario/historial";
    }

}
