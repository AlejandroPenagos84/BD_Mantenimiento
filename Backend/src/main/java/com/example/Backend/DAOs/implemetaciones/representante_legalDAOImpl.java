package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.representante_legalDAO;
import com.example.backend.modelDTO.representante_legalDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class representante_legalDAOImpl implements representante_legalDAO {

    private final Connection connection;

    public representante_legalDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public representante_legalDTO findById(String id) {
        String sql = "SELECT * FROM representante_legal WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToRepresentante(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<representante_legalDTO> findAll() {
        List<representante_legalDTO> representantes = new ArrayList<>();
        String sql = "SELECT * FROM representante_legal";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                representantes.add(mapResultSetToRepresentante(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return representantes;
    }

    @Override
    public void insert(representante_legalDTO representante) {
        String sql = "INSERT INTO representante_legal (k_identificador, k_id_cliente) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, representante.getK_identificador());
            stmt.setString(2, representante.getK_id_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(representante_legalDTO representante) {
        String sql = "UPDATE representante_legal SET k_id_cliente = ? WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, representante.getK_id_cliente());
            stmt.setString(2, representante.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM representante_legal WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private representante_legalDTO mapResultSetToRepresentante(ResultSet rs) throws SQLException {
        representante_legalDTO representante = new representante_legalDTO();
        representante.setK_identificador(rs.getString("k_identificador"));
        representante.setK_id_cliente(rs.getString("k_id_cliente"));
        return representante;
    }
}
