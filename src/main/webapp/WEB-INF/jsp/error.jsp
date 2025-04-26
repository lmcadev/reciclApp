<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error en la Aplicación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 text-center">

            <div class="card shadow-lg">
                <div class="card-body">
                    <h1 class="display-4 text-danger">¡Ups! Algo salió mal 😟</h1>
                    <p class="lead">
                        <% if (exception != null) { %>
                            Error: <strong><%= exception.getMessage() %></strong>
                        <% } else { %>
                            Parece que la página que buscas no existe o ha ocurrido un problema.
                        <% } %>
                    </p>

                    <hr>

                    <a href="<%= request.getContextPath() %>/" class="btn btn-success mt-3">Volver al Inicio</a>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Bootstrap scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
