<%@ page session="true" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String rol = (usuario != null) ? usuario.getRol().name() : "";
%>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #a2c0c6;">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Gestión de Residuos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navMenu">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <% if ("USUARIO".equals(rol)) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/usuario/historial">Historial</a>
                    </li>
                <% } else if ("ADMINISTRADOR".equals(rol)) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">Dashboard Admin</a>
                    </li>
                <% } else if ("EMPRESA_RECOLECTORA".equals(rol)) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/empresa/dashboard">Dashboard Empresa</a>
                    </li>
                <% } %>
            </ul>

            <% if (usuario != null) { %>
                <form action="${pageContext.request.contextPath}/logout" method="post" class="d-inline">
                    <button type="submit" class="btn btn-outline-light btn-sm">Cerrar Sesión</button>
                </form>
            <% } else { %>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-light btn-sm">Iniciar Sesión</a>
            <% } %>
        </div>
    </div>
</nav>

<!-- Bootstrap scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
