package com.example.backend.service;

import com.example.backend.DAOs.interfaces.equipo_clienteDAO;
import com.example.backend.DAOs.implemetaciones.equipo_clienteDAOImpl;
import com.example.backend.modelDTO.conexion.conexionBD;
import com.example.backend.modelDTO.equipo_clienteDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class equipo_clienteService {

    public List<equipo_clienteDTO> getAll(String user, String password) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            equipo_clienteDAO dao = new equipo_clienteDAOImpl(conn);
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los registros", e);
        }
    }

    public Optional<equipo_clienteDTO> getById(String user, String password, String id) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            equipo_clienteDAO dao = new equipo_clienteDAOImpl(conn);
            return Optional.ofNullable(dao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener por ID", e);
        }
    }

    public void save(String user, String password, equipo_clienteDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            equipo_clienteDAO dao = new equipo_clienteDAOImpl(conn);
            dao.insert(entity);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la entidad", e);
        }
    }

    public void update(String user, String password, String id, equipo_clienteDTO entity) {
        conexionBD conexionBD = new conexionBD(user, password);

        try (Connection conn = conexionBD.obtenerConexion()) {
            equipo_clienteDAO dao = new equipo_clienteDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("equipo_cliente con ID " + id + " no encontrado.");
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
            equipo_clienteDAO dao = new equipo_clienteDAOImpl(conn);
            if (dao.findById(id) == null) {
                throw new RuntimeException("equipo_cliente con ID " + id + " no encontrado.");
            }
            dao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }
}
