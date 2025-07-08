package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.marcaDAO;
import com.example.backend.modelDTO.marcaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class marcaDAOImpl implements marcaDAO {

    private final Connection connection;

    public marcaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public marcaDTO findById(String id) {
        String sql = "SELECT * FROM marca WHERE k_id_marca = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToMarca(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<marcaDTO> findAll() {
        List<marcaDTO> marcas = new ArrayList<>();
        String sql = "SELECT * FROM marca";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                marcas.add(mapResultSetToMarca(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return marcas;
    }

    @Override
    public void insert(marcaDTO marca) {
        String sql = "INSERT INTO marca (k_id_marca, n_nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, marca.getK_id_marca());
            stmt.setString(2, marca.getN_nombre_marca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(marcaDTO marca) {
        String sql = "UPDATE marca SET n_nombre = ? WHERE k_id_marca = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, marca.getN_nombre_marca());
            stmt.setString(2, marca.getK_id_marca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM marca WHERE k_id_marca = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private marcaDTO mapResultSetToMarca(ResultSet rs) throws SQLException {
        marcaDTO marca = new marcaDTO();
        marca.setK_id_marca(rs.getString("k_id_marca"));
        marca.setN_nombre_marca(rs.getString("n_nombre"));
        return marca;
    }
}
