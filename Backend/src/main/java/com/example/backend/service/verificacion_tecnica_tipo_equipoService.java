package com.example.backend.service;

import com.example.backend.DAOs.interfaces.verificacion_tecnica_tipo_equipoDAO;
import com.example.backend.DAOs.implemetaciones.verificacion_tecnica_tipo_equipoDAOImpl;
import com.example.backend.modelDTO.conexion.conexionBD;
import com.example.backend.modelDTO.verificacion_tecnica_tipo_equipoDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class verificacion_tecnica_tipo_equipoService {

    public List<verificacion_tecnica_tipo_equipoDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnica_tipo_equipoDAO dao = new verificacion_tecnica_tipo_equipoDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<verificacion_tecnica_tipo_equipoDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnica_tipo_equipoDAO dao = new verificacion_tecnica_tipo_equipoDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, verificacion_tecnica_tipo_equipoDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnica_tipo_equipoDAO dao = new verificacion_tecnica_tipo_equipoDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, verificacion_tecnica_tipo_equipoDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            verificacion_tecnica_tipo_equipoDAO dao = new verificacion_tecnica_tipo_equipoDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("verificacion_tecnica_tipo_equipo con ID " + id + " no encontrado.");
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
            verificacion_tecnica_tipo_equipoDAO dao = new verificacion_tecnica_tipo_equipoDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("verificacion_tecnica_tipo_equipo con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
