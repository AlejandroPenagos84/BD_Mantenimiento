package com.example.backend.service;

import com.example.backend.DAOs.interfaces.paisDAO;
import com.example.backend.DAOs.implemetaciones.paisDAOImpl;
import com.example.backend.modelDTO.conexion.conexionBD;
import com.example.backend.modelDTO.paisDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class paisService {

    public List<paisDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            paisDAO dao = new paisDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<paisDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            paisDAO dao = new paisDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, paisDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            paisDAO dao = new paisDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, paisDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            paisDAO dao = new paisDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("pais con ID " + id + " no encontrado.");
            }
            // entity.setId(id); // Descomenta si tu modelo tiene setter de ID
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la entidad", e);
        }
    }

    public void deleteById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            paisDAO dao = new paisDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("pais con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
