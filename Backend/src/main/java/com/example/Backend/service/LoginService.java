package com.example.backend.service;

import com.example.backend.modelDTO.conexion.conexionBD;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class LoginService {
    public boolean validateUser(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
