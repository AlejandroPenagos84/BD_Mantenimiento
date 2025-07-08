package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.mantenimientoDAO;
import com.example.backend.modelDTO.mantenimientoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mantenimientoDAOImpl implements mantenimientoDAO {

    private final Connection connection;

    public mantenimientoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public mantenimientoDTO findById(String id) {
        String sql = "SELECT * FROM mantenimiento WHERE k_id_mantenimiento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToMantenimiento(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<mantenimientoDTO> findAll() {
        List<mantenimientoDTO> mantenimientos = new ArrayList<>();
        String sql = "SELECT * FROM mantenimiento";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                mantenimientos.add(mapResultSetToMantenimiento(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mantenimientos;
    }

    @Override
    public void insert(mantenimientoDTO mantenimiento) {
        String sql = "INSERT INTO mantenimiento (k_id_mantenimiento, f_fecha_mantenimineto, n_peridiosidad, k_identificador) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mantenimiento.getK_id_mantenimiento());
            if (mantenimiento.getF_fecha_mantenimiento() != null) {
                stmt.setDate(2, new java.sql.Date(mantenimiento.getF_fecha_mantenimiento().getTime()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            stmt.setString(3, mantenimiento.getN_periodicidad());
            stmt.setString(4, mantenimiento.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(mantenimientoDTO mantenimiento) {
        String sql = "UPDATE mantenimiento SET f_fecha_mantenimineto = ?, n_peridiosidad = ?, k_identificador = ? WHERE k_id_mantenimiento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (mantenimiento.getK_id_mantenimiento() != null) {
                stmt.setDate(1, new java.sql.Date(mantenimiento.getF_fecha_mantenimiento().getTime()));
            } else {
                stmt.setNull(1, Types.DATE);
            }
            stmt.setString(2, mantenimiento.getN_periodicidad());
            stmt.setString(3, mantenimiento.getK_identificador());
            stmt.setString(4, mantenimiento.getK_id_mantenimiento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM mantenimiento WHERE k_id_mantenimiento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private mantenimientoDTO mapResultSetToMantenimiento(ResultSet rs) throws SQLException {
        mantenimientoDTO mantenimiento = new mantenimientoDTO();
        mantenimiento.setK_id_mantenimiento(rs.getString("k_id_mantenimiento"));
        mantenimiento.setF_fecha_mantenimiento(rs.getDate("f_fecha_mantenimineto"));
        mantenimiento.setN_periodicidad(rs.getString("n_peridiosidad"));
        mantenimiento.setK_identificador(rs.getString("k_identificador"));
        return mantenimiento;
    }
}
