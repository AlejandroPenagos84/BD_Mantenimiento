package com.example.backend.service;

import com.example.backend.DAOs.interfaces.fabricanteDAO;
import com.example.backend.DAOs.implemetaciones.fabricanteDAOImpl;
import com.example.backend.model.conexion.conexionBD;
import com.example.backend.modelDTO.fabricanteDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class fabricanteService {

    public List<fabricanteDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            fabricanteDAO dao = new fabricanteDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<fabricanteDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            fabricanteDAO dao = new fabricanteDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, fabricanteDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            fabricanteDAO dao = new fabricanteDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, fabricanteDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            fabricanteDAO dao = new fabricanteDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("fabricante con ID " + id + " no encontrado.");
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
            fabricanteDAO dao = new fabricanteDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("fabricante con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
