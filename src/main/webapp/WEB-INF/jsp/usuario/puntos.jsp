<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    int puntos = (Integer) request.getAttribute("puntos");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mis Puntos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5 text-center">
    <div class="card shadow-sm p-4">
        <h3>Hola <%= usuario.getNombre() %> 👋</h3>
        <p class="mt-3 fs-4">Tienes <strong class="text-success"><%= puntos %></strong> puntos acumulados.</p>
    </div>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
