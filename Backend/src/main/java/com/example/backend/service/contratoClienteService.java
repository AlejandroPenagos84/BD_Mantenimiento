package com.example.backend.service;

import com.example.backend.DAOs.implemetaciones.contratoClienteDAOImpl;
import com.example.backend.DAOs.interfaces.contratoClienteDAO;
import com.example.backend.model.conexion.conexionBD;
import com.example.backend.modelDTO.contratoClienteDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class contratoClienteService {
    public List<contratoClienteDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            contratoClienteDAO dao = new contratoClienteDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<contratoClienteDTO> getById(String user, String password, String k_id_contrato, String k_id_cliente) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            contratoClienteDAO dao = new contratoClienteDAOImpl(conn);
            return Optional.ofNullable(dao.findById(k_id_contrato,k_id_cliente));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }
}
