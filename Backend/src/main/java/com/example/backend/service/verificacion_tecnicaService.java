package com.example.backend.service;

import com.example.backend.DAOs.interfaces.verificacion_tecnicaDAO;
import com.example.backend.DAOs.implemetaciones.verificacion_tecnicaDAOImpl;
import com.example.backend.model.conexion.conexionBD;
import com.example.backend.modelDTO.verificacion_tecnicaDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class verificacion_tecnicaService {

    public List<verificacion_tecnicaDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnicaDAO dao = new verificacion_tecnicaDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<verificacion_tecnicaDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnicaDAO dao = new verificacion_tecnicaDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, verificacion_tecnicaDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnicaDAO dao = new verificacion_tecnicaDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, verificacion_tecnicaDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnicaDAO dao = new verificacion_tecnicaDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("verificacion_tecnica con ID " + id + " no encontrado.");
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
            verificacion_tecnicaDAO dao = new verificacion_tecnicaDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("verificacion_tecnica con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
