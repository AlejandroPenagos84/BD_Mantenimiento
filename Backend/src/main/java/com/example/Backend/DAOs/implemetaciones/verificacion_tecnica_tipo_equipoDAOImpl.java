package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.verificacion_tecnica_tipo_equipoDAO;
import com.example.backend.modelDTO.verificacion_tecnica_tipo_equipoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class verificacion_tecnica_tipo_equipoDAOImpl implements verificacion_tecnica_tipo_equipoDAO {

    private final Connection connection;

    public verificacion_tecnica_tipo_equipoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public verificacion_tecnica_tipo_equipoDTO findById(String id) {
        String sql = "SELECT * FROM verificacion_tecnica_tipo_equipo WHERE k_id_verificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToVtte(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<verificacion_tecnica_tipo_equipoDTO> findAll() {
        List<verificacion_tecnica_tipo_equipoDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM verificacion_tecnica_tipo_equipo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToVtte(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(verificacion_tecnica_tipo_equipoDTO vtte) {
        String sql = "INSERT INTO verificacion_tecnica_tipo_equipo (k_id_verificacion, k_id_tipo_equipo) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vtte.getK_id_verificacion());
            stmt.setString(2, vtte.getK_id_tipo_equipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(verificacion_tecnica_tipo_equipoDTO vtte) {
        String sql = "UPDATE verificacion_tecnica_tipo_equipo SET k_id_tipo_equipo = ? WHERE k_id_verificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vtte.getK_id_tipo_equipo());
            stmt.setString(2, vtte.getK_id_verificacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM verificacion_tecnica_tipo_equipo WHERE k_id_verificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private verificacion_tecnica_tipo_equipoDTO mapResultSetToVtte(ResultSet rs) throws SQLException {
        verificacion_tecnica_tipo_equipoDTO vtte = new verificacion_tecnica_tipo_equipoDTO();
        vtte.setK_id_verificacion(rs.getString("k_id_verificacion"));
        vtte.setK_id_tipo_equipo(rs.getString("k_id_tipo_equipo"));
        return vtte;
    }
}
