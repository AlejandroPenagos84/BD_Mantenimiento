package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.dato_metrologicoDAO;
import com.example.backend.modelDTO.dato_metrologicoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dato_metrologicoDAOImpl implements dato_metrologicoDAO {

    private final Connection connection;

    public dato_metrologicoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public dato_metrologicoDTO findById(String id) {
        String sql = "SELECT * FROM dato_metrologico WHERE k_id_tipo_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDato(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<dato_metrologicoDTO> findAll() {
        List<dato_metrologicoDTO> datos = new ArrayList<>();
        String sql = "SELECT * FROM dato_metrologico";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                datos.add(mapResultSetToDato(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return datos;
    }

    @Override
    public void insert(dato_metrologicoDTO dato) {
        String sql = "INSERT INTO dato_metrologico (d_valor, n_tipo, k_id_tipo_equipo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, dato.getD_valor());
            stmt.setString(2, dato.getN_tipo());
            stmt.setString(3, dato.getK_id_tipo_equipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(dato_metrologicoDTO dato) {
        String sql = "UPDATE dato_metrologico SET d_valor = ?, n_tipo = ?, k_id_tipo_equipo = ? WHERE k_id_tipo_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, dato.getD_valor());
            stmt.setString(2, dato.getN_tipo());
            stmt.setString(3, dato.getK_id_tipo_equipo());
            stmt.setString(4, dato.getK_id_tipo_equipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM dato_metrologico WHERE k_id_tipo_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private dato_metrologicoDTO mapResultSetToDato(ResultSet rs) throws SQLException {
        dato_metrologicoDTO dato = new dato_metrologicoDTO();
        dato.setD_valor(rs.getDouble("d_valor"));
        dato.setN_tipo(rs.getString("n_tipo"));
        dato.setK_id_tipo_equipo(rs.getString("k_id_tipo_equipo"));
        return dato;
    }
}
