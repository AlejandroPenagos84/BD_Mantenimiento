package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.tipo_equipoDAO;
import com.example.backend.modelDTO.tipo_equipoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class tipo_equipoDAOImpl implements tipo_equipoDAO {

    private final Connection connection;

    public tipo_equipoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public tipo_equipoDTO findById(String id) {
        String sql = "SELECT * FROM tipo_equipo WHERE k_id_tipo_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTipoEquipo(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<tipo_equipoDTO> findAll() {
        List<tipo_equipoDTO> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_equipo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tipos.add(mapResultSetToTipoEquipo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tipos;
    }

    @Override
    public void insert(tipo_equipoDTO tipo) {
        String sql = "INSERT INTO tipo_equipo (k_id_tipo_equipo, n_nombre, t_definicion_tecnica, t_recomendaciones_cuidado, i_voltage, d_amperaje, t_tecnologia_predominante, b_verificable, m_valor_unitario_mantenimiento, n_tipo_verificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo.getK_id_tipo_equipo());
            stmt.setString(2, tipo.getN_nombre_tipo_equipo());
            stmt.setString(3, tipo.getT_definicion_tecnica());
            stmt.setString(4, tipo.getT_recomendaciones_ciudado());
            if (tipo.getI_voltage() != null) {
                stmt.setInt(5, tipo.getI_voltage());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            if (tipo.getD_amperaje() != null) {
                stmt.setDouble(6, tipo.getD_amperaje());
            } else {
                stmt.setNull(6, Types.DOUBLE);
            }
            stmt.setString(7, tipo.getT_tecnologia_predominante());
            stmt.setBoolean(8, tipo.getB_verificable());
            stmt.setDouble(9, tipo.getM_valor_unitario_mantenimiento());
            stmt.setString(10, tipo.getN_tipo_verificacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(tipo_equipoDTO tipo) {
        String sql = "UPDATE tipo_equipo SET n_nombre = ?, t_definicion_tecnica = ?, t_recomendaciones_cuidado = ?, i_voltage = ?, d_amperaje = ?, t_tecnologia_predominante = ?, b_verificable = ?, m_valor_unitario_mantenimiento = ?, n_tipo_verificacion = ? WHERE k_id_tipo_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo.getN_nombre_tipo_equipo());
            stmt.setString(2, tipo.getT_definicion_tecnica());
            stmt.setString(3, tipo.getT_recomendaciones_ciudado());
            if (tipo.getI_voltage() != null) {
                stmt.setInt(4, tipo.getI_voltage());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            if (tipo.getD_amperaje() != null) {
                stmt.setDouble(5, tipo.getD_amperaje());
            } else {
                stmt.setNull(5, Types.DOUBLE);
            }
            stmt.setString(6, tipo.getT_tecnologia_predominante());
            stmt.setBoolean(7, tipo.getB_verificable());
            stmt.setDouble(8, tipo.getM_valor_unitario_mantenimiento());
            stmt.setString(9, tipo.getN_tipo_verificacion());
            stmt.setString(10, tipo.getK_id_tipo_equipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM tipo_equipo WHERE k_id_tipo_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private tipo_equipoDTO mapResultSetToTipoEquipo(ResultSet rs) throws SQLException {
        tipo_equipoDTO tipo = new tipo_equipoDTO();
        tipo.setK_id_tipo_equipo(rs.getString("k_id_tipo_equipo"));
        tipo.setN_nombre_tipo_equipo(rs.getString("n_nombre"));
        tipo.setT_definicion_tecnica(rs.getString("t_definicion_tecnica"));
        tipo.setT_recomendaciones_ciudado(rs.getString("t_recomendaciones_cuidado"));
        tipo.setI_voltage(rs.getObject("i_voltage") != null ? rs.getInt("i_voltage") : null);
        tipo.setD_amperaje(rs.getObject("d_amperaje") != null ? rs.getDouble("d_amperaje") : null);
        tipo.setT_tecnologia_predominante(rs.getString("t_tecnologia_predominante"));
        tipo.setB_verificable(rs.getBoolean("b_verificable"));
        tipo.setM_valor_unitario_mantenimiento(rs.getDouble("m_valor_unitario_mantenimiento"));
        tipo.setN_tipo_verificacion(rs.getString("n_tipo_verificacion"));
        return tipo;
    }
}
