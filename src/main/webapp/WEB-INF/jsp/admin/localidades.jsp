<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Localidades</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="d-flex justify-content-between mb-3">
        <h2>Localidades Registradas</h2>
        <a href="${pageContext.request.contextPath}/admin/localidades/nueva" class="btn btn-success">Añadir Nueva</a>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="table-primary">
                <tr>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="localidad" items="${localidades}">
                    <tr>
                        <td><c:out value="${localidad.nombre}" /></td>
                        <td class="d-flex gap-2">
                            <a href="${pageContext.request.contextPath}/admin/localidades/editar/${localidad.id}" class="btn btn-warning btn-sm">Editar</a>
                            <form action="${pageContext.request.contextPath}/admin/localidades/eliminar" method="post" onsubmit="return confirm('¿Eliminar esta localidad?');">
                                <input type="hidden" name="id" value="${localidad.id}" />
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                        </td>
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
