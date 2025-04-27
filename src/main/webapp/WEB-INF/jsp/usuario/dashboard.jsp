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
    <h2 class="text-center">Bienvenido, <%= usuario.getNombre() %></h2>
   
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="list-group">
        <a href="${pageContext.request.contextPath}/usuario/solicitarRecoleccion" class="list-group-item list-group-item-action">Solicitar recolección</a>
        <a href="${pageContext.request.contextPath}/usuario/historial" class="list-group-item list-group-item-action">Ver historial</a>
        <a href="${pageContext.request.contextPath}/usuario/puntos" class="list-group-item list-group-item-action">Mis puntos</a>
        <a href="${pageContext.request.contextPath}/usuario/notificaciones" class="list-group-item list-group-item-action">Notificaciones</a>        
        </div>
    </div>
</div>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
