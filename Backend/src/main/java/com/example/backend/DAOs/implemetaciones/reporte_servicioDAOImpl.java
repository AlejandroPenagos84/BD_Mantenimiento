package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.reporte_servicioDAO;
import com.example.backend.modelDTO.reporte_servicioDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reporte_servicioDAOImpl implements reporte_servicioDAO {

    private final Connection connection;

    public reporte_servicioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public reporte_servicioDTO findById(String id) {
        String sql = "SELECT * FROM reporte_servicio WHERE k_id_mantenimiento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToReporte(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<reporte_servicioDTO> findAll() {
        List<reporte_servicioDTO> reportes = new ArrayList<>();
        String sql = "SELECT * FROM reporte_servicio";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reportes.add(mapResultSetToReporte(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reportes;
    }

    @Override
    public void insert(reporte_servicioDTO reporte) {
        String sql = "INSERT INTO reporte_servicio (k_id_mantenimiento, k_id_equipo_cliente, k_id_cliente, k_id_verificacion_tecnica, t_observaciones_reporte_servico, d_valor_equipo, d_valor_patron, t_resultado_verificacion_tecnica) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reporte.getK_id_mantenimiento());
            stmt.setString(2, reporte.getK_id_equipo_cliente());
            stmt.setString(3, reporte.getK_id_cliente());
            stmt.setString(4, reporte.getK_id_verificacion_tecnica());
            stmt.setString(5, reporte.getT_observaciones_reporte_servico());

            if (reporte.getD_valor_equipo() != null) {
                stmt.setDouble(6, reporte.getD_valor_equipo());
            } else {
                stmt.setNull(6, Types.DOUBLE);
            }

            if (reporte.getD_valor_patron() != null) {
                stmt.setDouble(7, reporte.getD_valor_patron());
            } else {
                stmt.setNull(7, Types.DOUBLE);
            }

            stmt.setString(8, reporte.getT_resultado_verificacion_tecnica());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(reporte_servicioDTO reporte) {
        String sql = "UPDATE reporte_servicio SET k_id_equipo_cliente = ?, k_id_cliente = ?, k_id_verificacion_tecnica = ?, t_observaciones_reporte_servico = ?, d_valor_equipo = ?, d_valor_patron = ?, t_resultado_verificacion_tecnica = ? WHERE k_id_mantenimiento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reporte.getK_id_equipo_cliente());
            stmt.setString(2, reporte.getK_id_cliente());
            stmt.setString(3, reporte.getK_id_verificacion_tecnica());
            stmt.setString(4, reporte.getT_observaciones_reporte_servico());

            if (reporte.getD_valor_equipo() != null) {
                stmt.setDouble(5, reporte.getD_valor_equipo());
            } else {
                stmt.setNull(5, Types.DOUBLE);
            }

            if (reporte.getD_valor_patron() != null) {
                stmt.setDouble(6, reporte.getD_valor_patron());
            } else {
                stmt.setNull(6, Types.DOUBLE);
            }

            stmt.setString(7, reporte.getT_resultado_verificacion_tecnica());
            stmt.setString(8, reporte.getK_id_mantenimiento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM reporte_servicio WHERE k_id_mantenimiento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private reporte_servicioDTO mapResultSetToReporte(ResultSet rs) throws SQLException {
        reporte_servicioDTO reporte = new reporte_servicioDTO();
        reporte.setK_id_mantenimiento(rs.getString("k_id_mantenimiento"));
        reporte.setK_id_equipo_cliente(rs.getString("k_id_equipo_cliente"));
        reporte.setK_id_cliente(rs.getString("k_id_cliente"));
        reporte.setK_id_verificacion_tecnica(rs.getString("k_id_verificacion_tecnica"));
        reporte.setT_observaciones_reporte_servico(rs.getString("t_observaciones_reporte_servico"));
        reporte.setD_valor_equipo(rs.getObject("d_valor_equipo") != null ? rs.getDouble("d_valor_equipo") : null);
        reporte.setD_valor_patron(rs.getObject("d_valor_patron") != null ? rs.getDouble("d_valor_patron") : null);
        reporte.setT_resultado_verificacion_tecnica(rs.getString("t_resultado_verificacion_tecnica"));
        return reporte;
    }
}
