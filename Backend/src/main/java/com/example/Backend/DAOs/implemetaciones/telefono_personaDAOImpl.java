package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.telefono_personaDAO;
import com.example.backend.modelDTO.telefono_personaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class telefono_personaDAOImpl implements telefono_personaDAO {

    private final Connection connection;

    public telefono_personaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public telefono_personaDTO findById(String id) {
        String sql = "SELECT * FROM telefono_persona WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTelefonoPersona(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<telefono_personaDTO> findAll() {
        List<telefono_personaDTO> telefonos = new ArrayList<>();
        String sql = "SELECT * FROM telefono_persona";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                telefonos.add(mapResultSetToTelefonoPersona(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telefonos;
    }

    @Override
    public void insert(telefono_personaDTO telefono) {
        String sql = "INSERT INTO telefono_persona (n_numero, k_identificador) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefono.getN_numero());
            stmt.setString(2, telefono.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(telefono_personaDTO telefono) {
        String sql = "UPDATE telefono_persona SET n_numero = ? WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefono.getN_numero());
            stmt.setString(2, telefono.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM telefono_persona WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private telefono_personaDTO mapResultSetToTelefonoPersona(ResultSet rs) throws SQLException {
        telefono_personaDTO telefono = new telefono_personaDTO();
        telefono.setN_numero(rs.getString("n_numero"));
        telefono.setK_identificador(rs.getString("k_identificador"));
        return telefono;
    }
}
