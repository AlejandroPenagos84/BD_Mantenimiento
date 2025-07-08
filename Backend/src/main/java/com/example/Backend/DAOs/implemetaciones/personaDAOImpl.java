package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.personaDAO;
import com.example.backend.modelDTO.personaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class personaDAOImpl implements personaDAO {

    private final Connection connection;

    public personaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public personaDTO findById(String id) {
        String sql = "SELECT * FROM persona WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPersona(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<personaDTO> findAll() {
        List<personaDTO> personas = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                personas.add(mapResultSetToPersona(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public void insert(personaDTO persona) {
        String sql = "INSERT INTO persona (k_identificador, k_cedula, n_primer_nombre, n_segundo_nombre, n_primer_apellido, n_segundo_apellido, t_tipo_persona, t_segundo_tipo_persona) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, persona.getK_identificador());
            stmt.setString(2, persona.getK_cedula());
            stmt.setString(3, persona.getN_primer_nombre());
            stmt.setString(4, persona.getN_segundo_nombre());
            stmt.setString(5, persona.getN_primer_apellido());
            stmt.setString(6, persona.getN_segundo_apellido());
            stmt.setString(7, persona.getT_tipo_persona());
            if (persona.getT_segundo_tipo_persona() != null) {
                stmt.setString(8, persona.getT_segundo_tipo_persona());
            } else {
                stmt.setNull(8, Types.VARCHAR);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(personaDTO persona) {
        String sql = "UPDATE persona SET k_cedula = ?, n_primer_nombre = ?, n_segundo_nombre = ?, n_primer_apellido = ?, n_segundo_apellido = ?, t_tipo_persona = ?, t_segundo_tipo_persona = ? WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, persona.getK_cedula());
            stmt.setString(2, persona.getN_primer_nombre());
            stmt.setString(3, persona.getN_segundo_nombre());
            stmt.setString(4, persona.getN_primer_apellido());
            stmt.setString(5, persona.getN_segundo_apellido());
            stmt.setString(6, persona.getT_tipo_persona());
            if (persona.getT_segundo_tipo_persona() != null) {
                stmt.setString(7, persona.getT_segundo_tipo_persona());
            } else {
                stmt.setNull(7, Types.VARCHAR);
            }
            stmt.setString(8, persona.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM persona WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private personaDTO mapResultSetToPersona(ResultSet rs) throws SQLException {
        personaDTO persona = new personaDTO();
        persona.setK_identificador(rs.getString("k_identificador"));
        persona.setK_cedula(rs.getString("k_cedula"));
        persona.setN_primer_nombre(rs.getString("n_primer_nombre"));
        persona.setN_segundo_nombre(rs.getString("n_segundo_nombre"));
        persona.setN_primer_apellido(rs.getString("n_primer_apellido"));
        persona.setN_segundo_apellido(rs.getString("n_segundo_apellido"));
        persona.setT_tipo_persona(rs.getString("t_tipo_persona"));
        persona.setT_segundo_tipo_persona(rs.getString("t_segundo_tipo_persona"));
        return persona;
    }
}
