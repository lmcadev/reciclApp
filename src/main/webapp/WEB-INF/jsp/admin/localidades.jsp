<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Localidades</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9f0f2;
        }

        .custom-header {
            background-color: #6c8c94;
            color: white;
        }

        .custom-button {
            background-color: #6c8c94;
            color: white;
            border: none;
        }

        .custom-button:hover {
            background-color: #5d7b82;
        }

        .custom-table thead {
            background-color: #6c8c94;
            color: white;
        }

        .btn-close {
            background-color: white;
        }
    </style>
</head>

<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-center flex-grow-1">Localidades Registradas</h2>
        <a href="${pageContext.request.contextPath}/admin/localidades/nueva" class="btn custom-button">Añadir Nueva</a>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover bg-white shadow-sm custom-table">
            <thead>
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
                            <a href="${pageContext.request.contextPath}/admin/localidades/editar/${localidad.id}" class="btn custom-button btn-sm">Editar</a>
                            <form action="${pageContext.request.contextPath}/admin/localidades/eliminar" method="post" onsubmit="return confirm('¿Eliminar esta localidad?');">
                                <input type="hidden" name="id" value="${localidad.id}" />
                                <button type="submit" class="btn custom-button btn-sm">Eliminar</button>
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
