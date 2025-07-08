package com.example.backend.service;

import com.example.backend.DAOs.interfaces.telefono_personaDAO;
import com.example.backend.DAOs.implemetaciones.telefono_personaDAOImpl;
import com.example.backend.model.conexion.conexionBD;
import com.example.backend.modelDTO.telefono_personaDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class telefono_personaService {

    public List<telefono_personaDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            telefono_personaDAO dao = new telefono_personaDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<telefono_personaDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            telefono_personaDAO dao = new telefono_personaDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, telefono_personaDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            telefono_personaDAO dao = new telefono_personaDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, telefono_personaDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            telefono_personaDAO dao = new telefono_personaDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("telefono_persona con ID " + id + " no encontrado.");
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
            telefono_personaDAO dao = new telefono_personaDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("telefono_persona con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
