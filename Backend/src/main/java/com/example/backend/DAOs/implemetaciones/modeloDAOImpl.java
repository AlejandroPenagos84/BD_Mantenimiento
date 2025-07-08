package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.modeloDAO;
import com.example.backend.modelDTO.modeloDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class modeloDAOImpl implements modeloDAO {

    private final Connection connection;

    public modeloDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public modeloDTO findById(String id) {
        String sql = "SELECT * FROM modelo WHERE k_id_modelo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToModelo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<modeloDTO> findAll() {
        List<modeloDTO> modelos = new ArrayList<>();
        String sql = "SELECT * FROM modelo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                modelos.add(mapResultSetToModelo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelos;
    }

    @Override
    public void insert(modeloDTO modelo) {
        String sql = "INSERT INTO modelo (k_id_modelo, n_invima, k_id_fabricante, k_id_equipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, modelo.getK_id_modelo());
            stmt.setString(2, modelo.getN_invima());
            stmt.setString(3, modelo.getK_id_fabricante());
            stmt.setString(4, modelo.getK_id_equipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(modeloDTO modelo) {
        String sql = "UPDATE modelo SET n_invima = ?, k_id_fabricante = ?, k_id_equipo = ? WHERE k_id_modelo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, modelo.getN_invima());
            stmt.setString(2, modelo.getK_id_fabricante());
            stmt.setString(3, modelo.getK_id_equipo());
            stmt.setString(4, modelo.getK_id_modelo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM modelo WHERE k_id_modelo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private modeloDTO mapResultSetToModelo(ResultSet rs) throws SQLException {
        modeloDTO modelo = new modeloDTO();
        modelo.setK_id_modelo(rs.getString("k_id_modelo"));
        modelo.setN_invima(rs.getString("n_invima"));
        modelo.setK_id_fabricante(rs.getString("k_id_fabricante"));
        modelo.setK_id_equipo(rs.getString("k_id_equipo"));
        return modelo;
    }
}
