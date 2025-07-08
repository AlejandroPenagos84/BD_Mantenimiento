package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.contratoClienteDAO;
import com.example.backend.modelDTO.clienteDTO;
import com.example.backend.modelDTO.contratoClienteDTO;
import com.example.backend.modelDTO.contratoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class contratoClienteDAOImpl implements contratoClienteDAO {

    private final Connection connection;

    public contratoClienteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public contratoClienteDTO findById(String k_id_contrato, String k_id_cliente) {
        String sql = "SELECT * FROM ContratosClientes WHERE k_id_contrato = ? AND k_id_cliente = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, k_id_contrato);
            stmt.setString(2,k_id_cliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToContratoCliente(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<contratoClienteDTO> findAll() {
        String sql = "SELECT * FROM ContratosClientes;";
        List<contratoClienteDTO> contratosClienteDTO = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contratosClienteDTO.add(mapResultSetToContratoCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contratosClienteDTO;
    }

    private contratoClienteDTO mapResultSetToContratoCliente(ResultSet rs) throws SQLException {
        contratoClienteDTO contrato = new contratoClienteDTO();
        contrato.setK_id_contrato(rs.getString("k_id_contrato"));
        contrato.setK_id_cliente(rs.getString("k_id_cliente"));
        return contrato;
    }
}
