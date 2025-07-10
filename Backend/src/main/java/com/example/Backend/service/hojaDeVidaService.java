package com.example.backend.service;

import com.example.backend.DAOs.implemetaciones.hojaDeVidaDAOImpl;
import com.example.backend.DAOs.interfaces.hojaDeVidaDAO;
import com.example.backend.modelDTO.conexion.conexionBD;
import com.example.backend.modelDTO.hojaDeVidaDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class hojaDeVidaService {
    public List<hojaDeVidaDTO> findByCliente(String user, String password, String idCliente) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            hojaDeVidaDAO dao = new hojaDeVidaDAOImpl(conn);
            return dao.findAllByCliente(idCliente);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<hojaDeVidaDTO> findById(String user, String password, String idEquipoCliente) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            hojaDeVidaDAO dao = new hojaDeVidaDAOImpl(conn);
            return Optional.ofNullable(dao.findById(idEquipoCliente));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

}
