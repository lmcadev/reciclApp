package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.enums.Rol;
import com.poli.reciclApp.util.DBConnection;
import com.poli.reciclApp.util.UUIDGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

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
                        rol
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

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
                    Rol.valueOf(rs.getString("rol"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    
        return null;
    }
    
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
                    Rol.valueOf(rs.getString("rol"))
                );
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

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
                        Rol.valueOf(rs.getString("rol"))
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

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

    public boolean registrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (id, nombre, correo, contrasena, telefono, direccion, localidad_id, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, UUIDGenerator.generar());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getTelefono() != null ? usuario.getTelefono() : "");
            stmt.setString(6, usuario.getDireccion() != null ? usuario.getDireccion() : "");
            stmt.setString(6, usuario.getLocalidad() != null ? usuario.getLocalidad().getId() : "");
            stmt.setString(7, usuario.getRol().name());
    
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
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
}
