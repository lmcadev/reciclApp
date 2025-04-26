package com.poli.reciclApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificacionController {

    @GetMapping("/notificaciones")
    public String mostrarNotificaciones() {
        //TODO Implementar lógica para obtener notificaciones
        return "notificaciones"; // Busca notificaciones.jsp en /WEB-INF/jsp/
    }

    @PostMapping("/notificaciones")
    public String procesarNotificaciones() {
        //TODO Implementar lógica para procesar notificaciones
        return "redirect:/notificaciones"; // Después de procesar, redirige de nuevo a la lista
    }
}
