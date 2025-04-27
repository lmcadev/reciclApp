package com.poli.reciclApp.dao;

import com.poli.reciclApp.model.Recoleccion;
import com.poli.reciclApp.model.Residuo;
import com.poli.reciclApp.model.Usuario;
import com.poli.reciclApp.model.enums.EstadoRecoleccion;
import com.poli.reciclApp.model.enums.Frecuencia;
import com.poli.reciclApp.model.enums.TipoResiduo;
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

    public boolean registrarPeso(String idRecoleccion, float nuevoPeso) {
        String sql = "UPDATE residuo SET peso = ? WHERE id IN (SELECT residuo_id FROM recoleccion WHERE id = ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setFloat(1, nuevoPeso);
            stmt.setString(2, idRecoleccion);
    
            return stmt.executeUpdate() > 0;
    
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

    
    
    
    

    public List<Recoleccion> obtenerTodas() {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT r.*, u.nombre AS usuario_nombre, e.nombre AS empresa_nombre, res.tipo AS residuo_tipo, res.peso AS residuo_peso " +
                     "FROM recoleccion r " +
                     "LEFT JOIN usuario u ON r.usuario_id = u.id " +
                     "LEFT JOIN usuario e ON r.empresa_id = e.id " +
                     "LEFT JOIN residuo res ON r.residuo_id = res.id";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Recoleccion reco = new Recoleccion();
                reco.setId(rs.getString("id"));
    
               
                Timestamp timestamp = rs.getTimestamp("fecha_programada");
                if (timestamp != null) {
                    reco.setFechaProgramada(timestamp.toLocalDateTime());
                }
    
                // Usuario
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("usuario_nombre"));
                reco.setUsuario(usuario);
    
                // Empresa
                Usuario empresa = new Usuario();
                empresa.setNombre(rs.getString("empresa_nombre"));
                reco.setEmpresa(empresa);
    
                // Residuo
                Residuo residuo = new Residuo();
                residuo.setTipo(TipoResiduo.valueOf(rs.getString("residuo_tipo")));
                residuo.setPeso(rs.getFloat("residuo_peso"));
                reco.setResiduo(residuo);
    
                lista.add(reco);
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

    public List<Recoleccion> listarSinEmpresa() {
        List<Recoleccion> lista = new ArrayList<>();
        String sql = "SELECT id, usuario_id, residuo_id, fecha_programada FROM recoleccion WHERE empresa_id IS NULL";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Recoleccion reco = new Recoleccion();
                reco.setId(rs.getString("id"));
                reco.setFechaProgramada(rs.getTimestamp("fecha_programada").toLocalDateTime());
    
                String usuarioId = rs.getString("usuario_id");
                System.out.println("Usuario ID leído de la base: " + usuarioId);
                
                Usuario usuario = new UsuarioDAO().buscarPorId(usuarioId);
                System.out.println("Usuario cargado: " + (usuario != null ? usuario.getNombre() : "null"));
    
                reco.setUsuario(usuario);
    
                // Si tienes Residuo
                Residuo residuo = new ResiduoDAO().buscarPorId(rs.getString("residuo_id"));
                reco.setResiduo(residuo);
    
                lista.add(reco);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
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

            // Cargar Usuario asociado
            String usuarioId = rs.getString("usuario_id");
            Usuario usuario = new UsuarioDAO().buscarPorId(usuarioId);
            r.setUsuario(usuario);

            // Cargar Residuo asociado
            String residuoId = rs.getString("residuo_id");
            Residuo residuo = new ResiduoDAO().buscarPorId(residuoId);
            r.setResiduo(residuo);

            lista.add(r);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

}
