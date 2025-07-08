package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.area_servicioDAO;
import com.example.backend.modelDTO.area_servicioDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class area_servicioDAOImpl implements area_servicioDAO {

    private final Connection connection;

    public area_servicioDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public area_servicioDTO findById(String id) {
        String sql = "SELECT * FROM area_servicio WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToArea_servicio(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<area_servicioDTO> findAll() {
        String sql = "SELECT * FROM area_servicio;";
        List<area_servicioDTO> area_servico = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                area_servico.add(mapResultSetToArea_servicio(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return area_servico;
    }

    @Override
    public void insert(area_servicioDTO area_servicion) {
        String sql = "INSERT INTO area_servicio (k_id_area_servicio, n_nombre_area, k_id_sede)\n" +
                "VALUES (?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, area_servicion.getK_id_area_servicio());
            stmt.setString(2, area_servicion.getN_nombre_area());
            stmt.setString(3, area_servicion.getK_id_sede());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(area_servicioDTO area_servicio) {
        String sql = "UPDATE area_servicio\n" +
                "SET n_nombre_area = ?,\n" +
                "    k_id_sede = ?\n" +
                "WHERE k_id_area_servicio = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, area_servicio.getK_id_area_servicio());
            stmt.setString(2, area_servicio.getN_nombre_area());
            stmt.setString(3, area_servicio.getK_id_sede());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM area_servicio\n" +
                "WHERE k_id_area_servicio = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private area_servicioDTO mapResultSetToArea_servicio(ResultSet rs) throws SQLException {
        area_servicioDTO c = new area_servicioDTO();
        c.setK_id_area_servicio(rs.getString("k_id_area_servicio"));
        c.setN_nombre_area(rs.getString("n_nombre_area"));
        c.setK_id_sede(rs.getString("k_id_sede"));
        return c;
    }

}