package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Localidad;
import com.poli.reciclApp.util.DBConnection;
import com.poli.reciclApp.util.UUIDGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDAO {


    public boolean registrar(Localidad localidad) {
        String sql = "INSERT INTO localidad (id, nombre) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUIDGenerator.generar());
            stmt.setString(2, localidad.getNombre());

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Localidad buscarPorId(String id) {
        String sql = "SELECT * FROM localidad WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Localidad(
                    rs.getString("id"),
                    rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Localidad> listarTodas() {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT * FROM localidad ORDER BY nombre ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Localidad l = new Localidad(
                    rs.getString("id"),
                    rs.getString("nombre")
                );
                localidades.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidades;
    }

   
    public boolean actualizar(Localidad localidad) {
        String sql = "UPDATE localidad SET nombre = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, localidad.getNombre());
            stmt.setString(2, localidad.getId());

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    public boolean eliminar(String id) {
        String sql = "DELETE FROM localidad WHERE id = ?";

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
