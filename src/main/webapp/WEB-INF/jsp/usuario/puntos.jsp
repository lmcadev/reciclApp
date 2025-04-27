<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    int puntos = (Integer) request.getAttribute("puntos");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mis Puntos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5 text-center">
    <div class="card shadow-sm p-4">
        <h3>Hola <%= usuario.getNombre() %></h3>
        <p class="mt-3 fs-4">Tienes <strong class="text-success"><%= puntos %></strong> puntos acumulados.</p>

        <!-- Botón para abrir el Modal -->
        <button type="button" class="btn btn-primary mt-4" data-bs-toggle="modal" data-bs-target="#canjearModal">
            Canjear Puntos
        </button>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="canjearModal" tabindex="-1" aria-labelledby="canjearModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="canjearModalLabel">Canjea tus Puntos</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p>¡Puedes canjear tus puntos en los siguientes comercios!</p>
        <ul class="list-group">
          <li class="list-group-item">1. Exito</li>
          <li class="list-group-item">2. Carulla</li>
          <li class="list-group-item">3. Olimpica</li>
          <li class="list-group-item">4. Home center</li>
          <li class="list-group-item">5. Fallabella</li>
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-bs-dismiss="modal">¡Entendido!</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
