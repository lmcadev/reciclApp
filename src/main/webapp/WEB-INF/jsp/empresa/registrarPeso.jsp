<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Recoleccion" %>
<%
    List<Recoleccion> pendientes = (List<Recoleccion>) request.getAttribute("pendientes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Peso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5">
    <h3 class="text-center mb-4">Registrar Peso Recolectado</h3>
    <table class="table table-bordered bg-white shadow-sm">
        <thead class="table-warning">
            <tr>
                <th>Usuario</th>
                <th>Tipo Residuo</th>
                <th>Fecha</th>
                <th>Registrar Peso (kg)</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                for (Recoleccion r : pendientes) {
            %>
            <tr>
                <td><%= r.getUsuario().getNombre() %></td>
                <td><%= r.getResiduo().getTipo() %></td>
                <td><%= r.getFechaProgramada() %></td>
                <td>
                    <form action="empresa" method="post" class="d-flex">
                        <input type="hidden" name="action" value="registrarPeso"/>
                        <input type="hidden" name="idRecoleccion" value="<%= r.getId() %>"/>
                        <input type="number" name="peso" step="0.1" min="0" class="form-control me-2" required>
                        <button class="btn btn-primary">Registrar</button>
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
