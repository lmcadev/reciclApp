<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ page import="com.poli.reciclApp.model.Usuario" %>
        <%@ page import="com.poli.reciclApp.model.Recoleccion" %>
        <%@ page import="com.poli.reciclApp.model.enums.EstadoRecoleccion" %>

            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                <% Usuario usuario=(Usuario) session.getAttribute("usuario"); %>

                    <!DOCTYPE html>
                    <html lang="es">

                    <head>
                        <meta charset="UTF-8">
                        <title>Historial de Recolecciones</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                            rel="stylesheet">
                    </head>

                    <body class="bg-light">

                        <jsp:include page="../navbar.jsp" />

                        <div class="container mt-5">
                            <div class="text-center mb-4">
                                <h2>Hola, <%= usuario.getNombre() %> <small class="text-muted">(Usuario)</small></h2>
                                <h5 class="text-muted">Historial de recolecciones solicitadas</h5>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-bordered table-hover align-middle">
                                    <thead class="table-success">
                                        <tr>
                                            <th>Fecha Programada</th>
                                            <th>Turno</th>
                                            <th>Frecuencia</th>
                                            <th>Estado</th>
                                            <th>Puntos</th>
                                            <th>Acciones</th> <!-- Nueva columna de acciones -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="recoleccion" items="${historial}">
                                            <tr>
                                                <td>
                                                    <c:out value="${recoleccion.fechaProgramada}" />
                                                </td>
                                                <td>
                                                    <c:out value="${recoleccion.turno}" />
                                                </td>
                                                <td>
                                                    <c:out value="${recoleccion.frecuencia}" />
                                                </td>
                                                <td>
                                                    <c:out value="${recoleccion.estado}" />
                                                </td>
                                                <td>
                                                    <c:out value="${recoleccion.puntos}" />
                                                </td>
                                                <td class="d-flex gap-2">
                                                    <!-- Botón Cancelar -->
                                                    <td class="d-flex gap-2">
                                                        <form action="${pageContext.request.contextPath}/usuario/cancelarRecoleccion" method="post">
                                                            <input type="hidden" name="idRecoleccion" value="${recoleccion.id}" />
                                                            <c:if test="${recoleccion.estado != 'REALIZADA'}">
                                                                <button type="submit" class="btn btn-sm btn-danger"
                                                                    onclick="return confirm('¿Estás seguro de cancelar esta recolección?');">
                                                                    Cancelar
                                                                </button>
                                                            </c:if>
                                                        </form>
                                                    
                                                        
                                                    
                                                    
                                                    

                                                    <!-- Botón Editar -->
                                                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal"
                                                            data-bs-target="#editarModal${recoleccion.id}">
                                                            Editar
                                                        </button>
                                                </td>
                                            </tr>

                                            <!-- Modal para editar fecha -->
                                            <div class="modal fade" id="editarModal${recoleccion.id}" tabindex="-1"
                                                aria-labelledby="editarModalLabel${recoleccion.id}" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <form
                                                            action="${pageContext.request.contextPath}/usuario/editarRecoleccion"
                                                            method="post">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title"
                                                                    id="editarModalLabel${recoleccion.id}">Editar Fecha
                                                                    de Recolección</h5>
                                                                <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal"
                                                                    aria-label="Cerrar"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="hidden" name="idRecoleccion"
                                                                    value="${recoleccion.id}">
                                                                <div class="mb-3">
                                                                    <label for="nuevaFecha" class="form-label">Nueva
                                                                        Fecha y Hora</label>
                                                                    <input type="datetime-local" class="form-control"
                                                                        name="nuevaFecha" required>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-primary">Guardar
                                                                    Cambios</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                        </div>

                        <jsp:include page="../footer.jsp" />

                        <script
                            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                    </body>

                    </html>