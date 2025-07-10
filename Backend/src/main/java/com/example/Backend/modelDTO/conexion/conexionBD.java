package com.example.backend.modelDTO.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    private final String url = "jdbc:postgresql://localhost:5432/VFinalBD";
    private final String user;
    private final String password;

    public conexionBD(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}