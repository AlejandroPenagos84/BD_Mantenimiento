package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.ciudadDAO;
import com.example.backend.modelDTO.ciudadDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ciudadDAOImpl implements ciudadDAO {

    private final Connection connection;

    public ciudadDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ciudadDTO findById(String id) {
        String sql = "SELECT * FROM ciudad WHERE k_id_ciudad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCiudad(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<ciudadDTO> findAll() {
        List<ciudadDTO> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudad";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ciudades.add(mapResultSetToCiudad(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ciudades;
    }

    @Override
    public void insert(ciudadDTO ciudad) {
        String sql = "INSERT INTO ciudad (k_id_ciudad, n_nombre_ciudad, k_id_pais) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getK_id_ciudad());
            stmt.setString(2, ciudad.getN_nombre());
            stmt.setString(3, ciudad.getK_id_pais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ciudadDTO ciudad) {
        String sql = "UPDATE ciudad SET n_nombre = ?, k_id_pais = ? WHERE k_id_ciudad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getN_nombre());
            stmt.setString(2, ciudad.getK_id_pais());
            stmt.setString(3, ciudad.getK_id_ciudad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM ciudad WHERE k_id_ciudad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ciudadDTO mapResultSetToCiudad(ResultSet rs) throws SQLException {
        ciudadDTO ciudad = new ciudadDTO();
        ciudad.setK_id_ciudad(rs.getString("k_id_ciudad"));
        ciudad.setN_nombre(rs.getString("n_nombre"));
        ciudad.setK_id_pais(rs.getString("k_id_pais"));
        return ciudad;
    }
}
