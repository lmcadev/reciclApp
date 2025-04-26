<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Solicitar Recolección</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="../navbar.jsp" />
<div class="container mt-5">
    <h3 class="mb-4 text-center">Solicitar Recolección</h3>
    <form action="usuario" method="post" class="card p-4 shadow-sm">
        <input type="hidden" name="action" value="solicitarRecoleccion" />
        
        <div class="mb-3">
            <label class="form-label">Tipo de Residuo</label>
            <select name="tipoResiduo" class="form-select">
                <option value="ORGANICO">Orgánico</option>
                <option value="INORGANICO">Inorgánico</option>
                <option value="PELIGROSO">Peligroso</option>
            </select>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Peso estimado (kg)</label>
            <input type="number" name="peso" step="0.1" class="form-control" required>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Fecha deseada</label>
            <input type="date" name="fecha" class="form-control">
        </div>
        
        <div class="d-grid">
            <button type="submit" class="btn btn-success">Enviar solicitud</button>
        </div>
    </form>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
