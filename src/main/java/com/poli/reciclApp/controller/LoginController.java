package com.poli.reciclApp.controller;

import com.poli.reciclApp.model.Login;
import com.poli.reciclApp.model.enums.EstadoSesion;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
        }
        return "login"; // Retorna el nombre de la vista JSP
    }

    @PostMapping("/login")
    public void procesarLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
    

    
        Login login = new Login();
        boolean autenticado = login.iniciarSesion(correo, contrasena);
    
        if (autenticado) {
            System.out.println("Usuario autenticado correctamente.");
            session.setAttribute("usuario", login.getUsuario());
            session.setAttribute("estado", EstadoSesion.ACTIVA);
    
            switch (login.getUsuario().getRol()) {
                case ADMINISTRADOR:
                    response.sendRedirect("/admin/dashboard");
                    break;
                case EMPRESA_RECOLECTORA:
                    response.sendRedirect("/empresa/dashboard");
                    break;
                case USUARIO:
                    response.sendRedirect("/usuario/dashboard");
                    break;
            }
        } else {
            System.out.println("Falló la autenticación.");
            response.sendRedirect("/login?error=true");
        }
    }
    
}
