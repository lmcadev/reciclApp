<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.poli.reciclApp.model.Recoleccion" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>

<%
    List<Recoleccion> pendientes = (List<Recoleccion>) request.getAttribute("pendientes");
    List<Usuario> empresas = (List<Usuario>) request.getAttribute("empresas");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Asignar Recolecciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5">
    <h3 class="mb-4 text-center">Asignar Recolecciones a Empresas</h3>
    <table class="table table-bordered bg-white shadow-sm">
        <thead class="table-success">
            <tr>
                <th>Usuario</th>
                <th>Tipo de Residuo</th>
                <th>Peso</th>
                <th>Fecha</th>
                <th>Asignar a</th>
            </tr>
        </thead>
        <tbody>
            
            <%
                for (Recoleccion r : pendientes) {
            %>
            <tr>
                <td><%= r.getUsuario().getNombre() %></td>
                <td><%= r.getResiduo().getTipo() %></td>
                <td><%= r.getResiduo().getPeso() %> kg</td>
                <td><%= r.getFechaProgramada() %></td>
                <td>
                    <form action="admin" method="post" class="d-flex">
                        <input type="hidden" name="action" value="asignarRecoleccion"/>
                        <input type="hidden" name="idRecoleccion" value="<%= r.getId() %>"/>
                        <select name="empresaId" class="form-select me-2" required>
                            <% 
                                for (Usuario e : empresas) {  
                            %>
                            <option value="<%= e.getId() %>"><%= e.getNombre() %></option>
                            <% 
                                }
                            %>
                        </select>
                        
                        <button class="btn btn-sm btn-primary">Asignar</button>
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
