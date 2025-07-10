package com.example.backend.service;

import com.example.backend.DAOs.implemetaciones.registerDAOImpl;
import com.example.backend.DAOs.interfaces.registerDAO;
import com.example.backend.modelDTO.conexion.conexionBD;
import com.example.backend.modelDTO.registerDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class registerService {
    public void register(registerDTO registerDTO, String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            registerDAO dao = new registerDAOImpl(conn);
            dao.registerUser(registerDTO);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }
}
