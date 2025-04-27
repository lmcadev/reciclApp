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
    <title>Dashboard Empresa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="text-center mb-4">
        <h2>Bienvenido, <%= usuario.getNombre() %> <small class="text-muted">(Empresa)</small></h2>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/empresa/asignadas" class="list-group-item list-group-item-action">
                    Recolecciones Asignadas
                </a>
                <a href="${pageContext.request.contextPath}/empresa/registrarPeso" class="list-group-item list-group-item-action">
                    Registrar Pesos de Residuos
                </a>
                <a href="${pageContext.request.contextPath}/empresa/historial" class="list-group-item list-group-item-action">
                    Ver Historial de Recolecciones
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />

</body>
</html>
