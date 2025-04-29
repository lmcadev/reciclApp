<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.poli.reciclApp.model.Recoleccion" %>

<%
    List<Recoleccion> reportes = (List<Recoleccion>) request.getAttribute("reportes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Recolecciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .preview-box {
            border: 2px solid #c0c0c0;
            height: 320px;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #aaa;
            font-style: italic;
        }
        .main-box {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 30px;
            border-radius: 10px;
            margin-bottom: 40px;
        }
    </style>
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />

    <div class="container my-5">
        <!-- Sección superior: cuadro + filtros -->
        <div class="main-box">
            <div class="row">
                <!-- Filtros a la derecha -->
                <div class="col-md-6">
                    <h2 class="mb-4">Reports</h2>

                    <div class="mb-3">
                        <label class="form-label">Waste type</label>
                        <select class="form-select">
                            <option selected>Select one</option>
                            <!-- opciones -->
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Collection Date</label>
                        <input type="date" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Location</label>
                        <input type="text" class="form-control" placeholder="Type a location">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Format type</label>
                        <select class="form-select">
                            <option selected>Select one</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <button class="btn btn-outline-primary w-100">Schedule</button>
                    </div>
                </div>

                <!-- Cuadro de imagen a la izquierda -->
                <div class="col-md-6">
                    <div class="preview-box">
                        <!-- Puedes poner <img src="..." class="img-fluid" /> aquí -->
                    </div>
                </div>
                
            </div>
        </div>

        <!-- Tabla de resultados -->
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
                    <td><%= r.getUsuario() != null ? r.getUsuario().getNombre() : "No disponible" %></td>
                    <td><%= r.getEmpresa() != null ? r.getEmpresa().getNombre() : "No asignada" %></td>
                    <td><%= r.getResiduo() != null ? r.getResiduo().getTipo() : "No disponible" %></td>
                    <td><%= r.getResiduo() != null ? r.getResiduo().getPeso() : "N/A" %></td>
                    <td><%= r.getFechaProgramada() != null ? r.getFechaProgramada() : "Sin fecha" %></td>
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
