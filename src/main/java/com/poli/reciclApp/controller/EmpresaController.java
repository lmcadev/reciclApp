package com.poli.reciclApp.controller;

import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.dao.ResiduoDAO;
import com.poli.reciclApp.model.Recoleccion;
import com.poli.reciclApp.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "empresa/dashboard"; // Vista del dashboard de la empresa
    }

    @GetMapping("/asignadas")
    public String verRecoleccionesAsignadas(HttpSession session, Model model) {
        Usuario empresa = (Usuario) session.getAttribute("usuario");
        List<Recoleccion> asignadas = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
        model.addAttribute("recolecciones", asignadas);
        return "empresa/recoleccionesAsignadas";
    }

    @GetMapping("/registrarPeso")
    public String registrarPesoVista(HttpSession session, Model model) {
        Usuario empresa = (Usuario) session.getAttribute("usuario");
        List<Recoleccion> pendientes = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
        model.addAttribute("pendientes", pendientes);
        return "empresa/registrarPeso";
    }

    @PostMapping("/confirmarRecoleccion")
    public String confirmarRecoleccion(@RequestParam("idRecoleccion") String id) {
        new RecoleccionDAO().confirmarRecoleccion(id);
        return "redirect:/empresa/asignadas";
    }

    @PostMapping("/registrarPeso")
    public String registrarPeso(
            @RequestParam("idRecoleccion") String id,
            @RequestParam("peso") float peso) {
        new ResiduoDAO().actualizarPesoPorRecoleccion(id, peso);
        return "redirect:/empresa/registrarPeso";
    }
}
