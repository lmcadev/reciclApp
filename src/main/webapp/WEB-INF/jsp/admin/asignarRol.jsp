<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%@ page import="com.poli.reciclApp.model.Localidad" %>

<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");

    String mensajeExito = (String) request.getAttribute("mensajeExito");
    String mensajeError = (String) request.getAttribute("mensajeError");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9f0f2;
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
        }

        .custom-table thead {
            background-color: #6c8c94;
            color: white;
        }

        .form-select:focus,
        .form-control:focus {
            border-color: #6c8c94;
            box-shadow: 0 0 0 0.2rem rgba(108, 140, 148, 0.25);
        }

        .btn-close {
            background-color: white;
        }
    </style>
</head>
<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="text-center flex-grow-1">Gestión de Usuarios</h3>
        <button class="btn custom-button" data-bs-toggle="modal" data-bs-target="#modalAgregarUsuario">Agregar Usuario</button>
    </div>

    <% if (mensajeExito != null) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <%= mensajeExito %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    <% } %>
    <% if (mensajeError != null) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <%= mensajeError %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    <% } %>

    <table class="table table-striped table-hover bg-white shadow-sm custom-table">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% for (Usuario u : usuarios) { %>
            <tr>
                <td><%= u.getNombre() %></td>
                <td><%= u.getCorreo() %></td>
                <td><%= u.getRol() %></td>
                <td class="d-flex gap-2">
                    <button class="btn btn-sm custom-button" data-bs-toggle="modal" data-bs-target="#modalEditarUsuario<%= u.getId() %>">Editar</button>

                    <form action="${pageContext.request.contextPath}/admin/eliminarUsuario" method="post" onsubmit="return confirm('¿Seguro que deseas eliminar este usuario?');">
                        <input type="hidden" name="usuarioId" value="<%= u.getId() %>"/>
                        <button class="btn btn-sm custom-button">Eliminar</button>
                    </form>
                </td>
            </tr>

            <!-- Modal Editar Usuario -->
            <div class="modal fade" id="modalEditarUsuario<%= u.getId() %>" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin/editarUsuario" method="post">
                            <div class="modal-header custom-header">
                                <h5 class="modal-title">Editar Usuario</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="usuarioId" value="<%= u.getId() %>"/>

                                <div class="mb-3">
                                    <label>Nombre</label>
                                    <input type="text" name="nombre" class="form-control" value="<%= u.getNombre() %>" required>
                                </div>
                                <div class="mb-3">
                                    <label>Correo</label>
                                    <input type="email" name="correo" class="form-control" value="<%= u.getCorreo() %>" required>
                                </div>
                                <div class="mb-3">
                                    <label>Teléfono</label>
                                    <input type="text" name="telefono" class="form-control" value="<%= u.getTelefono() %>">
                                </div>
                                <div class="mb-3">
                                    <label>Dirección</label>
                                    <input type="text" name="direccion" class="form-control" value="<%= u.getDireccion() %>">
                                </div>
                                <div class="mb-3">
                                    <label>Localidad</label>
                                    <select name="localidad_id" class="form-select">
                                        <% if (localidades != null) { 
                                            for (Localidad loc : localidades) { %>
                                                <option value="<%= loc.getId() %>"><%= loc.getNombre() %></option>
                                        <%  } 
                                        } else { %>
                                            <option>No hay localidades disponibles</option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label>Rol</label>
                                    <select name="rol" class="form-select">
                                        <option value="USUARIO" <%= u.getRol().name().equals("USUARIO") ? "selected" : "" %>>USUARIO</option>
                                        <option value="EMPRESA_RECOLECTORA" <%= u.getRol().name().equals("EMPRESA_RECOLECTORA") ? "selected" : "" %>>EMPRESA_RECOLECTORA</option>
                                        <option value="ADMINISTRADOR" <%= u.getRol().name().equals("ADMINISTRADOR") ? "selected" : "" %>>ADMINISTRADOR</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn custom-button">Guardar Cambios</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <% } %>
        </tbody>
    </table>
</div>

<!-- Modal Agregar Usuario -->
<div class="modal fade" id="modalAgregarUsuario" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/admin/agregarUsuario" method="post">
                <div class="modal-header custom-header">
                    <h5 class="modal-title">Agregar Nuevo Usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label>Nombre</label>
                        <input type="text" name="nombre" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Correo</label>
                        <input type="email" name="correo" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Contraseña</label>
                        <input type="password" name="contrasena" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Teléfono</label>
                        <input type="text" name="telefono" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label>Dirección</label>
                        <input type="text" name="direccion" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label>Localidad</label>
                        <select name="localidad_id" class="form-select">
                            <% if (localidades != null) { 
                                for (Localidad loc : localidades) { %>
                                    <option value="<%= loc.getId() %>"><%= loc.getNombre() %></option>
                            <%  } 
                            } else { %>
                                <option>No hay localidades disponibles</option>
                            <% } %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label>Rol</label>
                        <select name="rol" class="form-select">
                            <option value="USUARIO">USUARIO</option>
                            <option value="EMPRESA_RECOLECTORA">EMPRESA_RECOLECTORA</option>
                            <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn custom-button">Agregar Usuario</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
