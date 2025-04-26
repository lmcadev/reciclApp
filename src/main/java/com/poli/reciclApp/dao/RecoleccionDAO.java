package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Recoleccion;
import com.poli.reciclApp.model.enums.EstadoRecoleccion;
import com.poli.reciclApp.model.enums.Frecuencia;
import com.poli.reciclApp.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecoleccionDAO {

    public boolean registrar(Recoleccion r) {
        String sql = "INSERT INTO recoleccion(id, usuario_id, residuo_id, fecha_programada, turno, frecuencia, estado, puntos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getId());
            stmt.setString(2, r.getUsuario().getId());
            stmt.setString(3, r.getResiduo().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(r.getFechaProgramada()));
            stmt.setString(5, r.getTurno());
            stmt.setString(6, r.getFrecuencia().name());
            stmt.setString(7, r.getEstado().name());
            stmt.setInt(8, r.getPuntos());

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Recoleccion> obtenerPorUsuario(String usuarioId) {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recoleccion WHERE usuario_id = ? ORDER BY fecha_programada DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Recoleccion r = new Recoleccion();
                r.setId(rs.getString("id"));
                r.setFechaProgramada(rs.getTimestamp("fecha_programada").toLocalDateTime());
                r.setTurno(rs.getString("turno"));
                r.setFrecuencia(Frecuencia.valueOf(rs.getString("frecuencia")));
                r.setEstado(EstadoRecoleccion.valueOf(rs.getString("estado")));
                r.setPuntos(rs.getInt("puntos"));
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Recoleccion> listarSinEmpresa() {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recoleccion WHERE empresa_id IS NULL";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Recoleccion r = new Recoleccion();
                r.setId(rs.getString("id"));
                r.setEstado(EstadoRecoleccion.valueOf(rs.getString("estado")));
                r.setFechaProgramada(rs.getTimestamp("fecha_programada").toLocalDateTime());
                
                // Cargar el Usuario
                String usuarioId = rs.getString("usuario_id");
                r.setUsuario(new UsuarioDAO().buscarPorId(usuarioId));
    
                // 🔥 Ahora: Cargar el Residuo también
                String residuoId = rs.getString("residuo_id");
                r.setResiduo(new ResiduoDAO().buscarPorId(residuoId)); // <-- necesitas este método
    
                lista.add(r);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
    }
    

    public List<Recoleccion> obtenerTodas() {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recoleccion ORDER BY fecha_programada DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Recoleccion r = new Recoleccion();
                r.setId(rs.getString("id"));
                r.setFechaProgramada(rs.getTimestamp("fecha_programada").toLocalDateTime());
                r.setTurno(rs.getString("turno"));
                r.setFrecuencia(Frecuencia.valueOf(rs.getString("frecuencia")));
                r.setEstado(EstadoRecoleccion.valueOf(rs.getString("estado")));
                r.setPuntos(rs.getInt("puntos"));
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean asignarEmpresa(String recoleccionId, String empresaId) {
        String sql = "UPDATE recoleccion SET empresa_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empresaId);
            stmt.setString(2, recoleccionId);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean confirmarRecoleccion(String id) {
        String sql = "UPDATE recoleccion SET estado = 'REALIZADA', fecha_recoleccion = NOW() WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Recoleccion> listarPorEmpresa(String empresaId) {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recoleccion WHERE empresa_id = ? ORDER BY fecha_programada DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empresaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Recoleccion r = new Recoleccion();
                r.setId(rs.getString("id"));
                r.setFechaProgramada(rs.getTimestamp("fecha_programada").toLocalDateTime());
                r.setEstado(EstadoRecoleccion.valueOf(rs.getString("estado")));
                r.setPuntos(rs.getInt("puntos"));
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
