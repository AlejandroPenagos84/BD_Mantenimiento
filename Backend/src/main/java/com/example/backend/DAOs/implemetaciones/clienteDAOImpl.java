package com.example.backend.DAOs.implemetaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.example.backend.DAOs.interfaces.*;
import com.example.backend.modelDTO.clienteDTO;

public class clienteDAOImpl implements clienteDAO {

    private final Connection connection;

    public clienteDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public clienteDTO findById(String id) {
        String sql = "SELECT * FROM cliente WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<clienteDTO> findAll() {
        String sql = "SELECT * FROM cliente;";
        List<clienteDTO> clientes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public void insert(clienteDTO cliente) {
        String sql = "INSERT INTO cliente (k_id_cliente, n_tipo_identificacion, n_razon_social, n_tipo_cliente, k_id_pais)\n" +
                "VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getK_id_cliente());
            stmt.setString(2, cliente.getN_tipo_identificacion());
            stmt.setString(3, cliente.getN_razon_social());
            stmt.setString(4, cliente.getN_tipo_cliente());
            stmt.setString(5, cliente.getK_id_pais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(clienteDTO cliente) {
        String sql = "UPDATE cliente\n" +
                "SET n_tipo_identificacion = ?,\n" +
                "    n_razon_social = ?,\n" +
                "    n_tipo_cliente = ?,\n" +
                "    k_id_pais = ?\n" +
                "WHERE k_id_cliente = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getN_tipo_identificacion());
            stmt.setString(2, cliente.getN_razon_social());
            stmt.setString(3, cliente.getN_tipo_cliente());
            stmt.setString(4, cliente.getK_id_pais());
            stmt.setString(5, cliente.getK_id_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM cliente\n" +
                "WHERE k_id_cliente = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public clienteDTO findByRazonSocial(String razonSocial) {
        String sql = "SELECT * FROM cliente WHERE razon_social = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, razonSocial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private clienteDTO mapResultSetToCliente(ResultSet rs) throws SQLException {
        clienteDTO c = new clienteDTO();
        c.setK_id_cliente(rs.getString("k_id_cliente"));
        c.setN_tipo_identificacion(rs.getString("n_tipo_identificacion"));
        c.setN_razon_social(rs.getString("n_razon_social"));
        c.setN_tipo_cliente(rs.getString("n_tipo_cliente"));
        c.setK_id_pais(rs.getString("k_id_pais"));
        return c;
    }

}