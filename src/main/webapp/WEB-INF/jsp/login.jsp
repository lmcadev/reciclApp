<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-white"> <!-- fondo blanco puro -->

    <div class="container mt-5">
        <div class="row justify-content-center align-items-center">
            <!-- Título centrado -->
            <h2 class="text-center mb-4 fw-bold">Mi Empresa S.A</h2>

            <div class="col-md-5">
                <div class="p-4"> <!-- sin card, ni sombra, ni fondo -->
                       
    
                    <!-- Formulario -->
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label for="correo" class="form-label fw-semibold">Correo Electrónico</label>
                            <input type="email" name="correo" id="correo" class="form-control border-0 border-bottom rounded-0" required autofocus style="background-color: transparent;">
                        </div>
    
                        <div class="mb-4">
                            <label for="contrasena" class="form-label fw-semibold">Contraseña</label>
                            <input type="password" name="contrasena" id="contrasena" class="form-control border-0 border-bottom rounded-0" required style="background-color: transparent;">
                        </div>
    
                        <button type="submit" class="btn w-100" style="background-color: transparent; border: 1px solid #ccc; color: #000;">Ingresar</button>
                    </form>
                </div>
            </div>
    
            <!-- Imagen -->
            <div class="col-md-5 text-center">
                <img src="${pageContext.request.contextPath}/imagenes/imagen1.jpg" alt="Imagen de Login" class="img-fluid" style="max-height: 400px;">
            </div>
        </div>
    </div>
    
</html>
