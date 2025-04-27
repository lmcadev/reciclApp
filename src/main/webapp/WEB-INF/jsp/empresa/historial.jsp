<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% Usuario empresa = (Usuario) session.getAttribute("usuario"); %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Historial de Recolecciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="text-center mb-4">
        <h2>Hola, <%= empresa.getNombre() %> <small class="text-muted">(Empresa)</small></h2>
        <h5 class="text-muted">Historial de recolecciones solicitadas</h5>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle">
            <thead class="table-primary">
                <tr>
                    <th>Fecha Programada</th>
                    <th>Turno</th>
                    <th>Frecuencia</th>
                    <th>Estado</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="recoleccion" items="${historial}">
                    <tr>
                        <td><c:out value="${recoleccion.fechaProgramada}" /></td>
                        <td><c:out value="${recoleccion.turno}" /></td>
                        <td><c:out value="${recoleccion.frecuencia}" /></td>
                        <td><c:out value="${recoleccion.estado}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
