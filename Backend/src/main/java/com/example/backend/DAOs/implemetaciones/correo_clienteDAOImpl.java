package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.correo_clienteDAO;
import com.example.backend.modelDTO.correo_clienteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class correo_clienteDAOImpl implements correo_clienteDAO {

    private final Connection connection;

    public correo_clienteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public correo_clienteDTO findById(String id) {
        String sql = "SELECT * FROM correo_cliente WHERE k_no_identificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCorreoCliente(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<correo_clienteDTO> findAll() {
        List<correo_clienteDTO> correos = new ArrayList<>();
        String sql = "SELECT * FROM correo_cliente";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                correos.add(mapResultSetToCorreoCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return correos;
    }

    @Override
    public void insert(correo_clienteDTO correo) {
        String sql = "INSERT INTO correo_cliente (n_correo_cliente, k_no_identificacion) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo.getN_correo_cliente());
            stmt.setString(2, correo.getK_id_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(correo_clienteDTO correo) {
        String sql = "UPDATE correo_cliente SET n_correo_cliente = ? WHERE k_no_identificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo.getN_correo_cliente());
            stmt.setString(2, correo.getK_id_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM correo_cliente WHERE k_no_identificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private correo_clienteDTO mapResultSetToCorreoCliente(ResultSet rs) throws SQLException {
        correo_clienteDTO correo = new correo_clienteDTO();
        correo.setN_correo_cliente(rs.getString("n_correo_cliente"));
        correo.setK_id_cliente(rs.getString("k_no_identificacion"));
        return correo;
    }
}
