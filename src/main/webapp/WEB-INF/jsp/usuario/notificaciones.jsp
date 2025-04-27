<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.poli.reciclApp.model.Notificacion" %>
<%
    List<Notificacion> notificaciones = (List<Notificacion>) request.getAttribute("notificaciones");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Notificaciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5">
    <h3 class="mb-4 text-center">Tus Notificaciones</h3>
    <div class="list-group">
        <%
            if (notificaciones != null && !notificaciones.isEmpty()) {
                for (Notificacion n : notificaciones) {
        %>
            <div class="list-group-item list-group-item-action">
                <strong><%= n.getTipo() %></strong><br>
                <%= n.getMensaje() %><br>
                <small class="text-muted">Enviada el <%= n.getFechaEnvio() %></small>
            </div>
        <%
                }
            } else {
        %>
            <div class="alert alert-info text-center">No tienes notificaciones nuevas.</div>
        <%
            }
        %>
    </div>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
