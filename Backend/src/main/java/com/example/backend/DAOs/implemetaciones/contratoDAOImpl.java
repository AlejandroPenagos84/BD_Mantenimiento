package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.contratoDAO;
import com.example.backend.modelDTO.contratoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class contratoDAOImpl implements contratoDAO {

    private final Connection connection;

    public contratoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public contratoDTO findById(String id) {
        String sql = "SELECT * FROM contrato WHERE k_id_contrato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToContrato(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<contratoDTO> findAll() {
        List<contratoDTO> contratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contratos.add(mapResultSetToContrato(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contratos;
    }

    @Override
    public void insert(contratoDTO contrato) {
        String sql = "INSERT INTO contrato (k_id_contrato, t_estado_contrato, f_fecha_inicio, f_fecha_finalizacion, t_observaciones) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contrato.getK_id_contrato());
            stmt.setString(2, contrato.getT_estado_contrato());
            stmt.setDate(3, (Date) contrato.getF_fecha_inicio());
            stmt.setDate(4, (Date) contrato.getF_fecha_finalizacion());
            stmt.setString(5, contrato.getT_observaciones());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(contratoDTO contrato) {
        String sql = "UPDATE contrato SET t_estado_contrato = ?, f_fecha_inicio = ?, f_fecha_finalizacion = ?, t_observaciones = ? WHERE k_id_contrato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contrato.getT_estado_contrato());
            stmt.setDate(2, (Date) contrato.getF_fecha_inicio());
            stmt.setDate(3, (Date) contrato.getF_fecha_finalizacion());
            stmt.setString(4, contrato.getT_observaciones());
            stmt.setString(5, contrato.getK_id_contrato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM contrato WHERE k_id_contrato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private contratoDTO mapResultSetToContrato(ResultSet rs) throws SQLException {
        contratoDTO contrato = new contratoDTO();
        contrato.setK_id_contrato(rs.getString("k_id_contrato"));
        contrato.setT_estado_contrato(rs.getString("t_estado_contrato"));
        contrato.setF_fecha_inicio(rs.getDate("f_fecha_inicio"));
        contrato.setF_fecha_finalizacion(rs.getDate("f_fecha_finalizacion"));
        contrato.setT_observaciones(rs.getString("t_observaciones"));
        return contrato;
    }
}
