package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.telefono_clienteDAO;
import com.example.backend.modelDTO.telefono_clienteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class telefono_clienteDAOImpl implements telefono_clienteDAO {

    private final Connection connection;

    public telefono_clienteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public telefono_clienteDTO findById(String id) {
        String sql = "SELECT * FROM telefono_cliente WHERE k_id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTelefonoCliente(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<telefono_clienteDTO> findAll() {
        List<telefono_clienteDTO> telefonos = new ArrayList<>();
        String sql = "SELECT * FROM telefono_cliente";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                telefonos.add(mapResultSetToTelefonoCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return telefonos;
    }

    @Override
    public void insert(telefono_clienteDTO telefono) {
        String sql = "INSERT INTO telefono_cliente (n_numero, k_id_cliente) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefono.getN_numero_cliente());
            stmt.setString(2, telefono.getK_id_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(telefono_clienteDTO telefono) {
        String sql = "UPDATE telefono_cliente SET n_numero = ? WHERE k_id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefono.getN_numero_cliente());
            stmt.setString(2, telefono.getK_id_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM telefono_cliente WHERE k_id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private telefono_clienteDTO mapResultSetToTelefonoCliente(ResultSet rs) throws SQLException {
        telefono_clienteDTO telefono = new telefono_clienteDTO();
        telefono.setN_numero_cliente(rs.getString("n_numero"));
        telefono.setK_id_cliente(rs.getString("k_id_cliente"));
        return telefono;
    }
}
