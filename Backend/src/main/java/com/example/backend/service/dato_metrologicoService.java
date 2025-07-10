package com.example.backend.service;

import com.example.backend.DAOs.interfaces.dato_metrologicoDAO;
import com.example.backend.DAOs.implemetaciones.dato_metrologicoDAOImpl;
import com.example.backend.modelDTO.conexion.conexionBD;
import com.example.backend.modelDTO.dato_metrologicoDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class dato_metrologicoService {

    public List<dato_metrologicoDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            dato_metrologicoDAO dao = new dato_metrologicoDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<dato_metrologicoDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            dato_metrologicoDAO dao = new dato_metrologicoDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, dato_metrologicoDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            dato_metrologicoDAO dao = new dato_metrologicoDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, dato_metrologicoDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            dato_metrologicoDAO dao = new dato_metrologicoDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("dato_metrologico con ID " + id + " no encontrado.");
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
            dato_metrologicoDAO dao = new dato_metrologicoDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("dato_metrologico con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
