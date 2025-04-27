package com.poli.reciclApp.controller;

import com.poli.reciclApp.dao.NotificacionDAO;
import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.dao.ResiduoDAO;
import com.poli.reciclApp.model.Notificacion;
import com.poli.reciclApp.model.Recoleccion;
import com.poli.reciclApp.model.Residuo;
import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.enums.EstadoRecoleccion;
import com.poli.reciclApp.model.enums.Frecuencia;
import com.poli.reciclApp.model.enums.TipoNotificacion;
import com.poli.reciclApp.model.enums.TipoResiduo;
import com.poli.reciclApp.model.enums.EstadoSesion;
import com.poli.reciclApp.util.UUIDGenerator;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/historial")
    public String verHistorial(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("historial", new RecoleccionDAO().obtenerPorUsuario(usuario.getId()));
        }
        return "usuario/historial";
    }

    @GetMapping("/dashboard")
public String dashboard(HttpSession session, Model model) {
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        model.addAttribute("usuario", usuario); 
    }
    return "usuario/dashboard";
}

    @GetMapping("/solicitarRecoleccion")
    public String solicitarRecoleccion(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("tiposResiduo", TipoResiduo.values());
            model.addAttribute("usuario", usuario);
        }
        return "usuario/solicitarRecoleccion";
    }



    @PostMapping("/solicitarRecoleccion")
    public String solicitarRecoleccion(
            @RequestParam("tipoResiduo") String tipoResiduo,
            @RequestParam("peso") float peso,
            @RequestParam("fechaHora") String fechaHora,
            HttpSession session) {

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

    // POST: cancelar una recolección
@PostMapping("/cancelarRecoleccion")
public String cancelarRecoleccion(@RequestParam("idRecoleccion") String id) {
    new RecoleccionDAO().cancelarRecoleccion(id);
    return "redirect:/usuario/historial";
}

// POST: editar fecha de recolección
@PostMapping("/editarRecoleccion")
public String editarRecoleccion(@RequestParam("idRecoleccion") String id,
                                @RequestParam("nuevaFecha") String nuevaFecha) {
    new RecoleccionDAO().editarFechaRecoleccion(id, LocalDateTime.parse(nuevaFecha));
    return "redirect:/usuario/historial";
}

}
