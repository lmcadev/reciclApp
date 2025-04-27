<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.poli.reciclApp.model.Recoleccion" %>
<%
    List<Recoleccion> pendientes = (List<Recoleccion>) request.getAttribute("pendientes");

    String mensajeExito = (String) request.getAttribute("mensajeExito");
    String mensajeError = (String) request.getAttribute("mensajeError");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Peso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">

    <h3 class="text-center mb-4">Registrar Peso Recolectado</h3>

    <!-- Mensajes de éxito o error -->
    <% if (mensajeExito != null) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <%= mensajeExito %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
        </div>
    <% } %>

    <% if (mensajeError != null) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <%= mensajeError %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
        </div>
    <% } %>

    <!-- Tabla de recolecciones pendientes -->
    <table class="table table-bordered bg-white shadow-sm">
        <thead class="table-warning">
            <tr>
                <th>Usuario</th>
                <th>Tipo Residuo</th>
                <th>Fecha Programada</th>
                <th>Registrar Peso (kg)</th>
            </tr>
        </thead>
        <tbody>
            <% if (pendientes != null && !pendientes.isEmpty()) { 
                for (Recoleccion r : pendientes) { %>
            <tr>
                <td><%= r.getUsuario() != null ? r.getUsuario().getNombre() : "Usuario no disponible" %></td>
                <td><%= r.getResiduo() != null ? r.getResiduo().getTipo() : "Residuo no disponible" %></td>
                <td><%= r.getFechaProgramada() != null ? r.getFechaProgramada() : "Sin fecha" %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/empresa/registrarPeso" method="post" class="d-flex">
                        <input type="hidden" name="idRecoleccion" value="<%= r.getId() %>"/>
                        <input type="number" name="peso" step="0.1" min="0" class="form-control me-2" required>
                        <button class="btn btn-primary">Registrar</button>
                    </form>
                </td>
            </tr>
            <% } 
            } else { %>
            <tr>
                <td colspan="4" class="text-center">No hay recolecciones pendientes</td>
            </tr>
            <% } %>
        </tbody>
    </table>

</div>

<jsp:include page="../footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
