<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Solicitar Recolección</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

    <jsp:include page="../navbar.jsp" />

    <div class="container mt-5">
        <div class="text-center mb-4">
            <h2>Hola, <%= usuario.getNombre() %> <small class="text-muted">(Usuario)</small></h2>
            <h5 class="text-muted">Solicita una nueva recolección</h5>
        </div>

        <div class="card shadow-sm">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/usuario/solicitarRecoleccion" method="post">
                    
                    <!-- Fecha Programada -->
                    <div class="mb-3">
                        <label for="fechaProgramada" class="form-label">Fecha Programada</label>
                        <input type="datetime-local" id="fechaProgramada" name="fechaProgramada" class="form-control" required />
                    </div>

                    <!-- Turno -->
                    <div class="mb-3">
                        <label for="turno" class="form-label">Turno</label>
                        <select id="turno" name="turno" class="form-select" required>
                            <option value="">Seleccione un turno</option>
                            <option value="MAÑANA">Mañana</option>
                            <option value="TARDE">Tarde</option>
                            <option value="NOCHE">Noche</option>
                        </select>
                    </div>

                    <!-- Frecuencia -->
                    <div class="mb-3">
                        <label for="frecuencia" class="form-label">Frecuencia</label>
                        <select id="frecuencia" name="frecuencia" class="form-select" required>
                            <option value="">Seleccione una frecuencia</option>
                            <option value="SEMANAL">Semanal</option>
                            <option value="QUINCENAL">Quincenal</option>
                            <option value="MENSUAL">Mensual</option>
                            <option value="BAJO_DEMANDA">Bajo Demanda</option>
                        </select>
                    </div>

                    <!-- Tipo de Residuo -->
                    <div class="mb-3">
                        <label for="tipoResiduo" class="form-label">Tipo de Residuo</label>
                        <select id="tipoResiduo" name="tipoResiduo" class="form-select" required>
                            <option value="">Seleccione un tipo de residuo</option>
                            <option value="INORGANICO">Inorgánico</option>
                            <option value="ORGANICO">Orgánico</option>
                            <option value="PELIGROSO">Peligroso</option>
                        </select>
                    </div>

                    <!-- Botón Solicitar -->
                    <div class="d-grid">
                        <button type="submit" class="btn btn-success">Solicitar Recolección</button>
                    </div>

                </form>
            </div>
        </div>

    </div>

    <jsp:include page="../footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
