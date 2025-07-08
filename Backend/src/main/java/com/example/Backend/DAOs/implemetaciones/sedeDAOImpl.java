package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.sedeDAO;
import com.example.backend.modelDTO.sedeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sedeDAOImpl implements sedeDAO {

    private final Connection connection;

    public sedeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public sedeDTO findById(String id) {
        String sql = "SELECT * FROM sede WHERE k_id_sede = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSede(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<sedeDTO> findAll() {
        List<sedeDTO> sedes = new ArrayList<>();
        String sql = "SELECT * FROM sede";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sedes.add(mapResultSetToSede(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sedes;
    }

    @Override
    public void insert(sedeDTO sede) {
        String sql = "INSERT INTO sede (k_id_sede, n_nombre, dir_calle, dir_carrera, dir_num, k_id_cliente, k_id_ciudad) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sede.getK_id_sede());
            stmt.setString(2, sede.getN_nombre_sede());
            stmt.setString(3, sede.getDir_calle());
            stmt.setString(4, sede.getDir_carrera());
            stmt.setString(5, sede.getDir_num());
            stmt.setString(6, sede.getK_id_cliente());
            stmt.setString(7, sede.getK_id_ciudad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(sedeDTO sede) {
        String sql = "UPDATE sede SET n_nombre = ?, dir_calle = ?, dir_carrera = ?, dir_num = ?, k_id_cliente = ?, k_id_ciudad = ? WHERE k_id_sede = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sede.getN_nombre_sede());
            stmt.setString(2, sede.getDir_calle());
            stmt.setString(3, sede.getDir_carrera());
            stmt.setString(4, sede.getDir_num());
            stmt.setString(5, sede.getK_id_cliente());
            stmt.setString(6, sede.getK_id_ciudad());
            stmt.setString(7, sede.getK_id_sede());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM sede WHERE k_id_sede = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private sedeDTO mapResultSetToSede(ResultSet rs) throws SQLException {
        sedeDTO sede = new sedeDTO();
        sede.setK_id_sede(rs.getString("k_id_sede"));
        sede.setN_nombre_sede(rs.getString("n_nombre"));
        sede.setDir_calle(rs.getString("dir_calle"));
        sede.setDir_carrera(rs.getString("dir_carrera"));
        sede.setDir_num(rs.getString("dir_num"));
        sede.setK_id_cliente(rs.getString("k_id_cliente"));
        sede.setK_id_ciudad(rs.getString("k_id_ciudad"));
        return sede;
    }
}
