<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.poli.reciclApp.model.Usuario" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Asignar Roles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="../navbar.jsp" />

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="text-center flex-grow-1">Gestión de Usuarios</h3>

        <!-- Botón para abrir modal de Agregar -->
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAgregarUsuario">
            Agregar Usuario
        </button>
    </div>

    <table class="table table-striped table-hover bg-white shadow-sm">
        <thead class="table-primary">
            <tr>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol actual</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Usuario u : usuarios) {
            %>
            <tr>
                <td><%= u.getNombre() %></td>
                <td><%= u.getCorreo() %></td>
                <td><%= u.getRol() %></td>
                <td class="d-flex gap-2">
                    <!-- Botón Editar (abre modal específico de cada usuario) -->
                    <button class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#modalEditarUsuario<%= u.getId() %>">
                        Editar
                    </button>

                    <!-- Botón Eliminar -->
                    <form action="${pageContext.request.contextPath}/admin/eliminarUsuario" method="post" onsubmit="return confirm('¿Eliminar este usuario?');">
                        <input type="hidden" name="usuarioId" value="<%= u.getId() %>"/>
                        <button class="btn btn-sm btn-danger">Eliminar</button>
                    </form>
                </td>
            </tr>

            <!-- Modal Editar Usuario -->
            <div class="modal fade" id="modalEditarUsuario<%= u.getId() %>" tabindex="-1" aria-labelledby="modalEditarUsuarioLabel<%= u.getId() %>" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/admin/editarUsuario" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalEditarUsuarioLabel<%= u.getId() %>">Editar Usuario</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
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
                                    <label>Telefonó</label>
                                    <input type="text" name="telefono" class="form-control" value="<%= u.getTelefono() %>">
                                </div>
                                <div class="mb-3">
                                    <label>Direccion</label>
                                    <input type="text" name="direccion" class="form-control" value="<%= u.getDireccion() %>">
                                </div>
                                <div class="mb-3">
                                    <label>Localidad</label>
                                    <input type="text" name="localidad_id" class="form-control" value="<%= u.getLocalidad() != null ? u.getLocalidad().getId() : "" %>">
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
                                <button type="submit" class="btn btn-success">Guardar Cambios</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<!-- Modal Agregar Usuario -->
<div class="modal fade" id="modalAgregarUsuario" tabindex="-1" aria-labelledby="modalAgregarUsuarioLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/admin/agregarUsuario" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalAgregarUsuarioLabel">Agregar Nuevo Usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
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
                        <label>Telefonó</label>
                        <input type="text" name="telefono" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label>Direccion</label>
                        <input type="text" name="direccion" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label>Localidad</label>
                        <input type="text" name="localidad_id" class="form-control">
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
                    <button type="submit" class="btn btn-primary">Agregar Usuario</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />

<!-- Bootstrap scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
