package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.enums.Rol;
import com.poli.reciclApp.util.DBConnection;
import com.poli.reciclApp.util.UUIDGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase UsuarioDAO que proporciona métodos para interactuar con la base de datos
 * y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la tabla "usuario".
 *
 * Métodos disponibles:
 * <ul>
 *   <li>{@link #listarPorRol(Rol)}: Recupera una lista de usuarios filtrados por su rol.</li>
 *   <li>{@link #buscarPorCorreo(String)}: Busca un usuario en la base de datos por su dirección de correo electrónico.</li>
 *   <li>{@link #buscarPorId(String)}: Busca un usuario en la base de datos por su identificador único.</li>
 *   <li>{@link #listarTodos()}: Recupera una lista de todos los usuarios de la base de datos.</li>
 *   <li>{@link #actualizarRol(String, Rol)}: Actualiza el rol de un usuario en la base de datos.</li>
 *   <li>{@link #registrar(Usuario)}: Registra un nuevo usuario en la base de datos.</li>
 *   <li>{@link #editarUsuario(String, String, String, String, String, String, Rol)}: Actualiza la información de un usuario en la base de datos.</li>
 *   <li>{@link #eliminar(String)}: Elimina un usuario de la base de datos basado en el ID proporcionado.</li>
 *   <li>{@link #existeCorreo(String)}: Verifica si una dirección de correo electrónico existe en la tabla "usuario".</li>
 * </ul>
 *
 * Notas:
 * <ul>
 *   <li>Los métodos utilizan conexiones a la base de datos proporcionadas por {@link DBConnection#getConnection()}.</li>
 *   <li>Los objetos {@link Usuario} representan los datos de los usuarios y se utilizan para mapear los resultados de las consultas.</li>
 *   <li>El manejo de excepciones se realiza mediante bloques try-catch para capturar errores de SQL.</li>
 * </ul>
 *
 * Ejemplo de uso:
 * <pre>
 * UsuarioDAO usuarioDAO = new UsuarioDAO();
 * List<Usuario> usuarios = usuarioDAO.listarPorRol(Rol.ADMIN);
 * Usuario usuario = usuarioDAO.buscarPorCorreo("correo@ejemplo.com");
 * </pre>
 */
public class UsuarioDAO {

    /**
     * Recupera una lista de usuarios filtrados por su rol.
     *
     * @param rol El rol por el cual se desea filtrar a los usuarios. Este es una instancia del enum {@link Rol}.
     * @return Una lista de objetos {@link Usuario} que tienen el rol especificado.
     *         Si no se encuentran usuarios con el rol dado, se devuelve una lista vacía.
     * @throws SQLException Si ocurre un error de acceso a la base de datos durante la ejecución de la consulta.
     */
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE rol = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rol.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        null,
                        rol,
                        rs.getInt("puntos")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Busca un usuario en la base de datos por su dirección de correo electrónico.
     *
     * @param correo La dirección de correo electrónico del usuario a buscar.
     * @return Un objeto {@link Usuario} que representa al usuario si se encuentra, o {@code null} si no se encuentra ningún usuario.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    public Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuario WHERE correo = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Usuario(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    null,
                    Rol.valueOf(rs.getString("rol")),
                    rs.getInt("puntos")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    
        return null;
    }
    
    /**
     * Busca un usuario en la base de datos por su identificador único.
     *
     * @param id El identificador único del usuario a buscar.
     * @return Un objeto {@link Usuario} que representa al usuario si se encuentra, o {@code null} si no se encuentra ningún usuario con el ID proporcionado.
     * 
     * Este método ejecuta una consulta SQL para recuperar la información del usuario en la tabla "usuario"
     * basada en el ID proporcionado. Si se encuentra un registro coincidente, crea y devuelve un objeto {@link Usuario}
     * con los detalles del usuario. Si no se encuentra ningún registro, devuelve {@code null}.
     */
    public Usuario buscarPorId(String id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Usuario(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    null,
                    Rol.valueOf(rs.getString("rol")),
                    rs.getInt("puntos")
                );
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * Recupera una lista de todos los usuarios de la base de datos.
     *
     * Este método ejecuta una consulta SQL para obtener todos los registros de la tabla "usuario"
     * y mapea cada registro a un objeto Usuario. La lista resultante de objetos Usuario
     * se devuelve.
     *
     * @return una lista de objetos Usuario que representan a todos los usuarios en la base de datos.
     *         Si no se encuentran usuarios, se devuelve una lista vacía.
     * @throws SQLException si ocurre un error de acceso a la base de datos o la consulta SQL es inválida.
     */
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        null,
                        Rol.valueOf(rs.getString("rol")), 0
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Actualiza el rol de un usuario en la base de datos.
     *
     * @param usuarioId El ID del usuario cuyo rol se desea actualizar.
     * @param nuevoRol El nuevo rol que se asignará al usuario.
     * @return {@code true} si el rol fue actualizado exitosamente, {@code false} en caso contrario.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    public boolean actualizarRol(String usuarioId, Rol nuevoRol) {
        String sql = "UPDATE usuario SET rol = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoRol.name());
            stmt.setString(2, usuarioId);
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto {@link Usuario} que contiene los detalles del usuario a registrar.
     * @return {@code true} si el usuario fue registrado exitosamente, {@code false} en caso contrario.
     * @throws SQLException Si el correo del usuario ya está registrado o si ocurre un error en la base de datos.
     *
     * Este método realiza los siguientes pasos:
     * <ul>
     *   <li>Verifica si el correo proporcionado en el objeto {@code usuario} ya está registrado en la base de datos.</li>
     *   <li>Si el correo no está registrado, genera un nuevo UUID para el ID del usuario si es nulo o está vacío.</li>
     *   <li>Inserta los detalles del usuario en la base de datos utilizando una declaración preparada.</li>
     *   <li>Registra los detalles del usuario en la consola para propósitos de depuración.</li>
     * </ul>
     *
     * Nota:
     * <ul>
     *   <li>El rol del usuario se almacena como una representación en cadena del enum {@code Rol}.</li>
     *   <li>Asegúrese de que la conexión a la base de datos esté correctamente configurada en {@link DBConnection#getConnection()}.</li>
     * </ul>
     */
    public boolean registrar(Usuario usuario) throws SQLException {
       
        if (existeCorreo(usuario.getCorreo())) {
            throw new SQLException("El correo ya está registrado: " + usuario.getCorreo());
        }
    
        String sql = "INSERT INTO usuario (id, nombre, correo, contrasena, telefono, direccion, localidad_id, rol) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Generar un nuevo UUID si el ID es nulo o vacío
            if (usuario.getId() == null || usuario.getId().isEmpty()) {
                usuario.setId(UUID.randomUUID().toString());
            }
    
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getDireccion());
            stmt.setString(7, usuario.getLocalidad());
          
            stmt.setString(8, usuario.getRol().name());

            System.out.println("Registrando usuario:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Contraseña: " + usuario.getContrasena());
            System.out.println("Teléfono: " + usuario.getTelefono());
            System.out.println("Dirección: " + usuario.getDireccion());
            System.out.println("Localidad ID: " + usuario.getLocalidad());
            System.out.println("Rol: " + usuario.getRol().name());
    
            return stmt.executeUpdate() == 1;
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    /**
     * Actualiza la información de un usuario en la base de datos.
     *
     * @param id           El identificador único del usuario a actualizar.
     * @param nombre       El nuevo nombre del usuario.
     * @param correo       El nuevo correo electrónico del usuario.
     * @param telefono     El nuevo número de teléfono del usuario.
     * @param direccion    La nueva dirección del usuario.
     * @param localidad_id El nuevo ID de localidad asociado al usuario.
     * @param rol          El nuevo rol del usuario, representado como un enum Rol.
     * @return true si el usuario fue actualizado exitosamente, false en caso contrario.
     */
    public boolean editarUsuario(String id, String nombre, String correo, String telefono, String direccion, String localidad_id, Rol rol) {
        String sql = "UPDATE usuario SET nombre = ?, correo = ?, telefono = ?, direccion = ?, localidad_id = ?,rol = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(2, telefono);
            stmt.setString(3, direccion);
            stmt.setString(3, localidad_id);
            stmt.setString(3, rol.name());
            stmt.setString(4, id);
    
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Elimina un usuario de la base de datos basado en el ID proporcionado.
     *
     * @param id El ID del usuario que se desea eliminar.
     * @return {@code true} si el usuario fue eliminado exitosamente, {@code false} en caso contrario.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    public boolean eliminar(String id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, id);
    
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verifica si una dirección de correo electrónico existe en la tabla "usuario".
     *
     * @param correo La dirección de correo electrónico a verificar.
     * @return {@code true} si la dirección de correo electrónico existe en la base de datos, {@code false} en caso contrario.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
