package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.encargadoDAO;
import com.example.backend.modelDTO.encargadoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class encargadoDAOImpl implements encargadoDAO {

    private final Connection connection;

    public encargadoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public encargadoDTO findById(String id) {
        String sql = "SELECT * FROM encargado WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEncargado(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<encargadoDTO> findAll() {
        List<encargadoDTO> encargados = new ArrayList<>();
        String sql = "SELECT * FROM encargado";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                encargados.add(mapResultSetToEncargado(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return encargados;
    }

    @Override
    public void insert(encargadoDTO encargado) {
        String sql = "INSERT INTO encargado (t_tipo_encargado, k_identificador, k_id_area_servicio, k_id_sede) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, encargado.getT_tipo_encargado());
            stmt.setString(2, encargado.getK_identificador());
            stmt.setString(3, encargado.getK_id_area_servicio());
            stmt.setString(4, encargado.getK_id_sede());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(encargadoDTO encargado) {
        String sql = "UPDATE encargado SET t_tipo_encargado = ?, k_id_area_servicio = ?, k_id_sede = ? WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, encargado.getT_tipo_encargado());
            stmt.setString(2, encargado.getK_id_area_servicio());
            stmt.setString(3, encargado.getK_id_sede());
            stmt.setString(4, encargado.getK_identificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM encargado WHERE k_identificador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private encargadoDTO mapResultSetToEncargado(ResultSet rs) throws SQLException {
        encargadoDTO encargado = new encargadoDTO();
        encargado.setT_tipo_encargado(rs.getString("t_tipo_encargado"));
        encargado.setK_identificador(rs.getString("k_identificador"));
        encargado.setK_id_area_servicio(rs.getString("k_id_area_servicio"));
        encargado.setK_id_sede(rs.getString("k_id_sede"));
        return encargado;
    }
}
