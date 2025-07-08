package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.paisDAO;
import com.example.backend.modelDTO.paisDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class paisDAOImpl implements paisDAO {

    private final Connection connection;

    public paisDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public paisDTO findById(String id) {
        String sql = "SELECT * FROM pais WHERE k_id_pais = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPais(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<paisDTO> findAll() {
        List<paisDTO> paises = new ArrayList<>();
        String sql = "SELECT * FROM pais";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                paises.add(mapResultSetToPais(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paises;
    }

    @Override
    public void insert(paisDTO pais) {
        String sql = "INSERT INTO pais (k_id_pais, n_nombre_pais) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pais.getK_id_pais());
            stmt.setString(2, pais.getN_nombre_pais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(paisDTO pais) {
        String sql = "UPDATE pais SET n_nombre = ? WHERE k_id_pais = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pais.getN_nombre_pais());
            stmt.setString(2, pais.getK_id_pais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM pais WHERE k_id_pais = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private paisDTO mapResultSetToPais(ResultSet rs) throws SQLException {
        paisDTO pais = new paisDTO();
        pais.setK_id_pais(rs.getString("k_id_pais"));
        pais.setN_nombre_pais(rs.getString("n_nombre_pais"));
        return pais;
    }
}
