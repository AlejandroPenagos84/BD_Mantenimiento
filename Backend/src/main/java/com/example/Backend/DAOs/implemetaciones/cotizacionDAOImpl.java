package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.cotizacionDAO;
import com.example.backend.modelDTO.cotizacionDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cotizacionDAOImpl implements cotizacionDAO {

    private final Connection connection;

    public cotizacionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public cotizacionDTO findById(String id) {
        String sql = "SELECT * FROM cotizacion WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCotizacion(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<cotizacionDTO> findAll() {
        List<cotizacionDTO> cotizaciones = new ArrayList<>();
        String sql = "SELECT * FROM cotizacion";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cotizaciones.add(mapResultSetToCotizacion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotizaciones;
    }

    @Override
    public void insert(cotizacionDTO cotizacion) {
        String sql = "INSERT INTO cotizacion (k_id_equipo_cliente, k_id_contrato, m_valor_unitario_mantenimiento, m_valor_total, t_observacion, f_fecha_emision, f_validez, t_estado_cotizacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cotizacion.getK_id_equipo_cliente());
            stmt.setString(2, cotizacion.getK_id_contrato());
            stmt.setDouble(3, cotizacion.getM_valor_unitario_mantenimiento());
            stmt.setDouble(4, cotizacion.getM_valor_total());
            stmt.setString(5, cotizacion.getT_observacion());
            stmt.setDate(6, (Date) cotizacion.getF_fecha_emision());
            stmt.setDate(7, (Date) cotizacion.getF_validez());
            stmt.setString(8, cotizacion.getT_estado_cotizacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(cotizacionDTO cotizacion) {
        String sql = "UPDATE cotizacion SET k_id_contrato = ?, m_valor_unitario_mantenimiento = ?, m_valor_total = ?, t_observacion = ?, f_fecha_emision = ?, f_validez = ?, t_estado_cotizacion = ? WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cotizacion.getK_id_contrato());
            stmt.setDouble(2, cotizacion.getM_valor_unitario_mantenimiento());
            stmt.setDouble(3, cotizacion.getM_valor_total());
            stmt.setString(4, cotizacion.getT_observacion());
            stmt.setDate(5, (Date) cotizacion.getF_fecha_emision());
            stmt.setDate(6, (Date) cotizacion.getF_validez());
            stmt.setString(7, cotizacion.getT_estado_cotizacion());
            stmt.setString(8, cotizacion.getK_id_equipo_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM cotizacion WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private cotizacionDTO mapResultSetToCotizacion(ResultSet rs) throws SQLException {
        cotizacionDTO cotizacion = new cotizacionDTO();
        cotizacion.setK_id_equipo_cliente(rs.getString("k_id_equipo_cliente"));
        cotizacion.setK_id_contrato(rs.getString("k_id_contrato"));
        cotizacion.setM_valor_unitario_mantenimiento(rs.getDouble("m_valor_unitario_mantenimiento"));
        cotizacion.setM_valor_total(rs.getDouble("m_valor_total"));
        cotizacion.setT_observacion(rs.getString("t_observacion"));
        cotizacion.setF_fecha_emision(rs.getDate("f_fecha_emision"));
        cotizacion.setF_validez(rs.getDate("f_validez"));
        cotizacion.setT_estado_cotizacion(rs.getString("t_estado_cotizacion"));
        return cotizacion;
    }
}
