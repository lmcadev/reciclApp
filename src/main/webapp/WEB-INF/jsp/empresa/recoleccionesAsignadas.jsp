<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.poli.reciclApp.model.Recoleccion" %>
<%
    List<Recoleccion> recolecciones = (List<Recoleccion>) request.getAttribute("recolecciones");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Recolecciones Asignadas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5">
    <h3 class="text-center mb-4">Recolecciones Asignadas</h3>
    <table class="table table-hover bg-white shadow-sm">
        <thead class="table-info">
            <tr>
                <th>Usuario</th>
                <th>Tipo de Residuo</th>
                <th>Peso Reportado</th>
                <th>Fecha</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                for (Recoleccion r : recolecciones) {
            %>
            <tr>
                <td><%= r.getUsuario().getNombre() %></td>
                <td><%= r.getResiduo().getTipo() %></td>
                <td><%= r.getResiduo().getPeso() %> kg</td>
                <td><%= r.getFechaProgramada() %></td>
                <td>
                    <form action="empresa" method="post" class="d-flex">
                        <input type="hidden" name="action" value="confirmarRecoleccion"/>
                        <input type="hidden" name="idRecoleccion" value="<%= r.getId() %>"/>
                        <button class="btn btn-sm btn-success">Confirmar</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
