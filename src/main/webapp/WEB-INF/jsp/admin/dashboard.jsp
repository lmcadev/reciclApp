<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administrador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="text-center mb-4">
        <h2>Bienvenido, <%= usuario.getNombre() %> <small class="text-muted">(Administrador)</small></h2>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/asignar" class="list-group-item list-group-item-action">
                    Asignar Recolecciones
                </a>
                <a href="${pageContext.request.contextPath}/admin/roles" class="list-group-item list-group-item-action">
                    Gestion de Usuarios
                </a>
                <a href="${pageContext.request.contextPath}/admin/reporte" class="list-group-item list-group-item-action">
                    Generar Reportes de Recolección
                </a>

                <a href="${pageContext.request.contextPath}/admin/localidades" class="list-group-item list-group-item-action">
                        Gestionar Localidades
                </a>
                
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />

</body>
</html>
