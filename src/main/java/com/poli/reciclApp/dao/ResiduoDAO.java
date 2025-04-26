package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Residuo;
import com.poli.reciclApp.model.enums.TipoResiduo;
import com.poli.reciclApp.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class ResiduoDAO {

    public boolean registrar(Residuo residuo) {
        String sql = "INSERT INTO residuo (id, tipo, peso, fecha_registro) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, residuo.getId());
            stmt.setString(2, residuo.getTipo().name());
            stmt.setDouble(3, residuo.getPeso());
            stmt.setTimestamp(4, Timestamp.valueOf(residuo.getFechaRegistro()));

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

   

    public boolean actualizarPeso(String id, float nuevoPeso) {
        String sql = "UPDATE residuo SET peso = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, nuevoPeso);
            stmt.setString(2, id);

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Residuo crearResiduoBasico(TipoResiduo tipo, float peso) {
        Residuo r = new Residuo();
        r.setId(UUID.randomUUID().toString());
        r.setTipo(tipo);
        r.setPeso(peso);
        r.setFechaRegistro(LocalDateTime.now());
        return r;
    }

    public boolean actualizarPesoPorRecoleccion(String idRecoleccion, float nuevoPeso) {
        String sql = "UPDATE residuo SET peso = ? WHERE id = (SELECT residuo_id FROM recoleccion WHERE id = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, nuevoPeso);
            stmt.setString(2, idRecoleccion);

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Residuo buscarPorId(String id) {
        String sql = "SELECT * FROM residuo WHERE id = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                Residuo residuo = new Residuo();
                residuo.setId(rs.getString("id"));
                residuo.setTipo(TipoResiduo.valueOf(rs.getString("tipo")));
                residuo.setPeso(rs.getFloat("peso"));
                residuo.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                return residuo;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }
    
}
