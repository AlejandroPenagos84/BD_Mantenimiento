package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.verificacion_tecnicaDAO;
import com.example.backend.modelDTO.verificacion_tecnicaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class verificacion_tecnicaDAOImpl implements verificacion_tecnicaDAO {

    private final Connection connection;

    public verificacion_tecnicaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public verificacion_tecnicaDTO findById(String id) {
        String sql = "SELECT * FROM verificacion_tecnica WHERE k_id_verificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToVerificacion(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<verificacion_tecnicaDTO> findAll() {
        List<verificacion_tecnicaDTO> verificaciones = new ArrayList<>();
        String sql = "SELECT * FROM verificacion_tecnica";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                verificaciones.add(mapResultSetToVerificacion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verificaciones;
    }

    @Override
    public void insert(verificacion_tecnicaDTO verificacion) {
        String sql = "INSERT INTO verificacion_tecnica (k_id_verificacion, t_descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, verificacion.getK_id_verificacion());
            stmt.setString(2, verificacion.getT_descripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(verificacion_tecnicaDTO verificacion) {
        String sql = "UPDATE verificacion_tecnica SET t_descripcion = ? WHERE k_id_verificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, verificacion.getT_descripcion());
            stmt.setString(2, verificacion.getK_id_verificacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM verificacion_tecnica WHERE k_id_verificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private verificacion_tecnicaDTO mapResultSetToVerificacion(ResultSet rs) throws SQLException {
        verificacion_tecnicaDTO verificacion = new verificacion_tecnicaDTO();
        verificacion.setK_id_verificacion(rs.getString("k_id_verificacion"));
        verificacion.setT_descripcion(rs.getString("t_descripcion"));
        return verificacion;
    }
}
