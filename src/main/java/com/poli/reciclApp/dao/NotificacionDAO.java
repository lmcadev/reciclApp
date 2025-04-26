package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Notificacion;
import com.poli.reciclApp.model.enums.EstadoSesion;
import com.poli.reciclApp.model.enums.TipoNotificacion;
import com.poli.reciclApp.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificacionDAO {

    public boolean registrar(Notificacion n) {
        String sql = "INSERT INTO notificacion (id, usuario_id, mensaje, fecha_envio, tipo, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, n.getId());
            stmt.setString(2, n.getUsuario().getId());
            stmt.setString(3, n.getMensaje());
            stmt.setTimestamp(4, Timestamp.valueOf(n.getFechaEnvio()));
            stmt.setString(5, n.getTipo().name());
            stmt.setString(6, n.getEstado().name());

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Notificacion> listarPorUsuario(String usuarioId) {
        List<Notificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM notificacion WHERE usuario_id = ? ORDER BY fecha_envio DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notificacion n = new Notificacion();
                n.setId(rs.getString("id"));
                n.setMensaje(rs.getString("mensaje"));
                n.setFechaEnvio(rs.getTimestamp("fecha_envio").toLocalDateTime());
                n.setTipo(TipoNotificacion.valueOf(rs.getString("tipo")));
                n.setEstado(EstadoSesion.valueOf(rs.getString("estado")));
                lista.add(n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean marcarComoLeida(String id) {
        String sql = "UPDATE notificacion SET estado = 'CERRADA' WHERE id = ?";

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
