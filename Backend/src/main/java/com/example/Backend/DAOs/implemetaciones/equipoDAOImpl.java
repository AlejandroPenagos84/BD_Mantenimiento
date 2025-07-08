package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.equipoDAO;
import com.example.backend.modelDTO.equipoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class equipoDAOImpl implements equipoDAO {

    private final Connection connection;

    public equipoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public equipoDTO findById(String id) {
        String sql = "SELECT * FROM equipo WHERE k_id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEquipo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<equipoDTO> findAll() {
        List<equipoDTO> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                equipos.add(mapResultSetToEquipo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    @Override
    public void insert(equipoDTO equipo) {
        String sql = "INSERT INTO equipo (k_id_equipo, k_id_tipo_equipo, k_id_marca) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getK_id_equipo());
            stmt.setString(2, equipo.getK_id_tipo_equipo());
            stmt.setString(3, equipo.getK_id_marca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(equipoDTO equipo) {
        String sql = "UPDATE equipo SET k_id_tipo_equipo = ?, k_id_marca = ? WHERE k_id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getK_id_tipo_equipo());
            stmt.setString(2, equipo.getK_id_marca());
            stmt.setString(3, equipo.getK_id_equipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM equipo WHERE k_id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private equipoDTO mapResultSetToEquipo(ResultSet rs) throws SQLException {
        equipoDTO equipo = new equipoDTO();
        equipo.setK_id_equipo(rs.getString("k_id_equipo"));
        equipo.setK_id_tipo_equipo(rs.getString("k_id_tipo_equipo"));
        equipo.setK_id_marca(rs.getString("k_id_marca"));
        return equipo;
    }
}
