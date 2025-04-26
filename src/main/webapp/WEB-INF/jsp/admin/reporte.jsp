<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Recoleccion" %>
<%
    List<Recoleccion> reportes = (List<Recoleccion>) request.getAttribute("reportes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Recolecciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5">
    <h3 class="mb-4 text-center">Reporte General de Recolecciones</h3>
    <table class="table table-bordered bg-white shadow-sm">
        <thead class="table-dark text-white">
            <tr>
                <th>Usuario</th>
                <th>Empresa</th>
                <th>Tipo Residuo</th>
                <th>Peso</th>
                <th>Fecha</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                for (Recoleccion r : reportes) {
            %>
            <tr>
                <td><%= r.getUsuario().getNombre() %></td>
                <td><%= r.getEmpresa() != null ? r.getEmpresa().getNombre() : "No asignada" %></td>
                <td><%= r.getResiduo().getTipo() %></td>
                <td><%= r.getResiduo().getPeso() %></td>
                <td><%= r.getFechaProgramada() %></td>
                <td><%= r.getEstado() %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
<jsp:include page="../footer.jsp" />

</html>
