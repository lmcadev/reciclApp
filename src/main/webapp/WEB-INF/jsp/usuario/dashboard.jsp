<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-4">
    <h2 class="text-center">Bienvenido, <%= usuario.getNombre() %> 👋</h2>
    <div class="row mt-4">
        <div class="col-md-3"><a href="solicitarRecoleccion.jsp" class="btn btn-outline-success w-100">Solicitar recolección</a></div>
        <div class="col-md-3"><a href="historial.jsp" class="btn btn-outline-primary w-100">Ver historial</a></div>
        <div class="col-md-3"><a href="puntos.jsp" class="btn btn-outline-warning w-100">Mis puntos</a></div>
        <div class="col-md-3"><a href="notificaciones.jsp" class="btn btn-outline-info w-100">Notificaciones</a></div>
    </div>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
