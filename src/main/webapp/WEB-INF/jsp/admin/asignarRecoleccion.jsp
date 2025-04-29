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
    <style>
        body {
            background-color: #e9f0f2; /* fondo suave */
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
            color: white;
        }
        .custom-select:focus {
            border-color: #6c8c94;
            box-shadow: 0 0 0 0.2rem rgba(108, 140, 148, 0.25);
        }
    </style>
</head>
<body>

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <h3 class="mb-4 text-center">Asignar Recolecciones a Empresas</h3>

    <div class="table-responsive">
        <table class="table table-bordered bg-white shadow-sm rounded">
            <thead class="custom-header">
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
                            <select name="empresaId" class="form-select me-2 custom-select" required>
                                <% for (Usuario e : empresas) { %>
                                <option value="<%= e.getId() %>"><%= e.getNombre() %></option>
                                <% } %>
                            </select>
                            <button class="btn btn-sm custom-button">Asignar</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../footer.jsp" />

</body>
</html>
