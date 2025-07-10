package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.personaEncargadoDAO;
import com.example.backend.DAOs.interfaces.registerDAO;
import com.example.backend.modelDTO.registerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class registerDAOImpl implements registerDAO {
    private final Connection connection;

    public registerDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerUser(registerDTO registerDTO) {
        String user = registerDTO.getUser();
        String password = registerDTO.getPassword().replace("'", "''"); // Escapar comillas simples
        String role = registerDTO.getRol();

        String createUserSql = "CREATE USER \"" + user + "\" WITH PASSWORD '" + password + "'";
        String grantRoleSql = "GRANT \"" + role + "\" TO \"" + user + "\"";

        try (
                Statement stmt = connection.createStatement()
        ) {
            stmt.executeUpdate(createUserSql);
            stmt.executeUpdate(grantRoleSql);

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el usuario o asignar rol: " + e.getMessage(), e);
        }
    }
}
