package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.fabricanteDAO;
import com.example.backend.modelDTO.fabricanteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class fabricanteDAOImpl implements fabricanteDAO {

    private final Connection connection;

    public fabricanteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public fabricanteDTO findById(String id) {
        String sql = "SELECT * FROM fabricante WHERE k_id_fabricante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToFabricante(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<fabricanteDTO> findAll() {
        List<fabricanteDTO> fabricantes = new ArrayList<>();
        String sql = "SELECT * FROM fabricante";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                fabricantes.add(mapResultSetToFabricante(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fabricantes;
    }

    @Override
    public void insert(fabricanteDTO fabricante) {
        String sql = "INSERT INTO fabricante (k_id_fabricante, n_nombre, k_id_pais) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fabricante.getK_id_fabricante());
            stmt.setString(2, fabricante.getN_nombre());
            stmt.setString(3, fabricante.getK_id_pais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(fabricanteDTO fabricante) {
        String sql = "UPDATE fabricante SET n_nombre = ?, k_id_pais = ? WHERE k_id_fabricante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fabricante.getN_nombre());
            stmt.setString(2, fabricante.getK_id_pais());
            stmt.setString(3, fabricante.getK_id_fabricante());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM fabricante WHERE k_id_fabricante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private fabricanteDTO mapResultSetToFabricante(ResultSet rs) throws SQLException {
        fabricanteDTO fabricante = new fabricanteDTO();
        fabricante.setK_id_fabricante(rs.getString("k_id_fabricante"));
        fabricante.setN_nombre(rs.getString("n_nombre"));
        fabricante.setK_id_pais(rs.getString("k_id_pais"));
        return fabricante;
    }
}
