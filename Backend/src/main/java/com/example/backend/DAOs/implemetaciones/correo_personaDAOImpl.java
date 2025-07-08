package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.correo_personaDAO;
import com.example.backend.modelDTO.correo_personaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class correo_personaDAOImpl implements correo_personaDAO {

    private final Connection connection;

    public correo_personaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public correo_personaDTO findById(String id) {
        String sql = "SELECT * FROM correo_persona WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCorreoPersona(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<correo_personaDTO> findAll() {
        List<correo_personaDTO> correos = new ArrayList<>();
        String sql = "SELECT * FROM correo_persona";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                correos.add(mapResultSetToCorreoPersona(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return correos;
    }

    @Override
    public void insert(correo_personaDTO correo) {
        String sql = "INSERT INTO correo_persona (n_correo_persona, k_identificador) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo.getN_correo_persona());
            stmt.setString(2, correo.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(correo_personaDTO correo) {
        String sql = "UPDATE correo_persona SET n_correo_persona = ? WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo.getN_correo_persona());
            stmt.setString(2, correo.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM correo_persona WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private correo_personaDTO mapResultSetToCorreoPersona(ResultSet rs) throws SQLException {
        correo_personaDTO correo = new correo_personaDTO();
        correo.setN_correo_persona(rs.getString("n_correo_persona"));
        correo.setK_identificador(rs.getString("k_identificador"));
        return correo;
    }
}
