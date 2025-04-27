package com.poli.reciclApp.controller;

import com.poli.reciclApp.dao.RecoleccionDAO;
import com.poli.reciclApp.model.Recoleccion;
import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.Empresa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la entidad "Empresa".
 * Este controlador proporciona endpoints para gestionar el dashboard, las recolecciones
 * asignadas.¡
 * 
 * Endpoints disponibles:
 * - GET /empresa/dashboard: Muestra el dashboard de la empresa.
 * - GET /empresa/asignadas: Lista las recolecciones asignadas a la empresa autenticada.
 * - GET /empresa/registrarPeso: Muestra la vista para registrar el peso de una recolección.
 * - GET /empresa/historial: Muestra el historial de la empresa autenticada.
 * - POST /empresa/confirmarRecoleccion: Confirma una recolección específica.
 * - POST /empresa/registrarPeso: Registra el peso de una recolección específica.
 * 
 * Este controlador utiliza objetos de sesión para identificar a la empresa autenticada
 * y pasar datos a las vistas correspondientes mediante el modelo.
 * 
 * Dependencias:
 * - HttpSession: Para recuperar información de la sesión actual.
 * - Model: Para pasar datos a las vistas.
 * - RedirectAttributes: Para manejar mensajes flash en redirecciones.
 * - RecoleccionDAO: Para interactuar con los datos de las recolecciones.
 * 
 * Nota: Asegúrese de que los objetos de sesión estén correctamente configurados
 * y que las vistas mencionadas existan en el proyecto.
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    /**
     * Maneja las solicitudes GET al endpoint "/dashboard".
     * 
     * @return El nombre de la vista para el dashboard de la empresa ("empresa/dashboard").
     *         Esta vista representa la interfaz del dashboard para la empresa.
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "empresa/dashboard"; // Vista del dashboard de la empresa
    }

    /**
     * Maneja la solicitud GET para ver la lista de recolecciones asignadas a una empresa.
     * 
     * Este método recupera el usuario de la empresa actualmente autenticado desde la sesión,
     * obtiene la lista de recolecciones asignadas a la empresa utilizando el ID de la empresa,
     * y agrega la lista al modelo para ser mostrada en la vista "empresa/recoleccionesAsignadas".
     * 
     * @param session el objeto de sesión HTTP utilizado para recuperar el usuario autenticado
     * @param model el objeto modelo utilizado para pasar datos a la vista
     * @return el nombre de la vista a renderizar, "empresa/recoleccionesAsignadas"
     */
    @GetMapping("/asignadas")
    public String verRecoleccionesAsignadas(HttpSession session, Model model) {
        Usuario empresa = (Usuario) session.getAttribute("usuario");
        List<Recoleccion> asignadas = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
        model.addAttribute("recolecciones", asignadas);
        return "empresa/recoleccionesAsignadas";
    }

    /**
     * Maneja la solicitud GET para el endpoint "registrarPeso".
     * Este método recupera la lista de recolecciones asociadas con la empresa autenticada
     * y prepara los datos para ser mostrados en la vista "registrarPeso".
     *
     * @param session el objeto de sesión HTTP utilizado para recuperar el usuario autenticado (empresa).
     * @param model   el objeto modelo utilizado para pasar atributos a la vista.
     * @return el nombre de la vista a renderizar ("empresa/registrarPeso").
     */
    @GetMapping("/registrarPeso")
    public String registrarPesoVista(HttpSession session, Model model) {
        Usuario empresa = (Usuario) session.getAttribute("usuario");
        List<Recoleccion> recolecciones = new RecoleccionDAO().listarPorEmpresa(empresa.getId());
        model.addAttribute("recolecciones", recolecciones);
        return "empresa/registrarPeso"; // Vista para registrar peso
    }

    /**
     * Maneja la solicitud HTTP GET para ver el historial de una empresa.
     * 
     * Este método recupera la información de la empresa desde la sesión HTTP actual
     * y, si la empresa existe, invoca el método para consultar su historial.
     * 
     * @param session la sesión HTTP actual que contiene la información de la empresa
     * @param model   el objeto modelo utilizado para pasar atributos a la vista
     */
    @GetMapping("/historial")
    public void verHistorialEmpresa(HttpSession session, Model model) {

        Empresa empresa = (Empresa) session.getAttribute("empresa");
        if (empresa != null) {
            empresa.consultarHistorial(session, model);
        }
    }

    /**
     * Maneja la confirmación de un proceso de recolección.
     *
     * Este método está mapeado a la solicitud POST para el endpoint "/confirmarRecoleccion".
     * Recibe el ID de la recolección a confirmar como un parámetro de la solicitud,
     * procesa la confirmación utilizando el RecoleccionDAO y redirige al usuario
     * a la página "asignadas" en la sección "empresa".
     *
     * @param id El ID de la recolección a confirmar, proporcionado como un parámetro de la solicitud.
     * @return Una cadena de redirección a la página "empresa/asignadas".
     */
    @PostMapping("/confirmarRecoleccion")
    public String confirmarRecoleccion(@RequestParam("idRecoleccion") String id) {
        new RecoleccionDAO().confirmarRecoleccion(id);
        return "redirect:/empresa/asignadas";
    }

    /**
     * Maneja el registro del peso para una recolección específica.
     *
     * @param recoId El ID de la recolección para la cual se está registrando el peso.
     * @param peso El peso a registrar, representado como un número flotante.
     * @param redirectAttributes Utilizado para pasar atributos flash a la vista redirigida.
     * @return Una URL de redirección a la página que muestra las recolecciones pendientes.
     *
     * Este método utiliza el RecoleccionDAO para registrar el peso de la recolección
     * especificada. Si la operación es exitosa, se agrega un mensaje de éxito a los
     * atributos flash. De lo contrario, se agrega un mensaje de error. Luego, el usuario
     * es redirigido a la página que muestra las recolecciones pendientes.
     */
    @PostMapping("/registrarPeso")
    public String registrarPeso(@RequestParam("idRecoleccion") String recoId,
            @RequestParam("peso") float peso,
            RedirectAttributes redirectAttributes) {
        boolean exito = new RecoleccionDAO().registrarPeso(recoId, peso);

        if (exito) {
            redirectAttributes.addFlashAttribute("mensajeExito", "Peso registrado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al registrar el peso.");
        }

        return "redirect:/empresa/pendientes"; // Ajusta la URL al JSP donde estás mostrando la tabla
    }


}
