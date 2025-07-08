package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.equipo_clienteDAO;
import com.example.backend.modelDTO.equipo_clienteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class equipo_clienteDAOImpl implements equipo_clienteDAO {

    private final Connection connection;

    public equipo_clienteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public equipo_clienteDTO findById(String id) {
        String sql = "SELECT * FROM equipo_cliente WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEquipoCliente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<equipo_clienteDTO> findAll() {
        List<equipo_clienteDTO> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo_cliente";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                equipos.add(mapResultSetToEquipoCliente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    @Override
    public void insert(equipo_clienteDTO equipo) {
        String sql = "INSERT INTO equipo_cliente (k_id_equipo_cliente, k_serie, f_fecha_compra, v_valor_compra, n_no_inventario, k_id_area_servicio, k_id_modelo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getK_id_equipo_cliente());
            stmt.setString(2, equipo.getK_serie());
            if (equipo.getF_fecha_compra() != null) {
                stmt.setDate(3, new java.sql.Date(equipo.getF_fecha_compra().getTime()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            if (equipo.getV_valor_compra() != null) {
                stmt.setDouble(4, equipo.getV_valor_compra());
            } else {
                stmt.setNull(4, Types.DOUBLE);
            }
            stmt.setString(5, equipo.getN_no_inventario());
            stmt.setString(6, equipo.getK_id_area_servicio());
            stmt.setString(7, equipo.getK_id_modelo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(equipo_clienteDTO equipo) {
        String sql = "UPDATE equipo_cliente SET k_serie = ?, f_fecha_compra = ?, v_valor_compra = ?, n_no_inventario = ?, k_id_area_servicio = ?, k_id_modelo = ? WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getK_serie());
            if (equipo.getF_fecha_compra() != null) {
                stmt.setDate(2, new java.sql.Date(equipo.getF_fecha_compra().getTime()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            if (equipo.getV_valor_compra() != null) {
                stmt.setDouble(3, equipo.getV_valor_compra());
            } else {
                stmt.setNull(3, Types.DOUBLE);
            }
            stmt.setString(4, equipo.getN_no_inventario());
            stmt.setString(5, equipo.getK_id_area_servicio());
            stmt.setString(6, equipo.getK_id_modelo());
            stmt.setString(7, equipo.getK_id_equipo_cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM equipo_cliente WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private equipo_clienteDTO mapResultSetToEquipoCliente(ResultSet rs) throws SQLException {
        equipo_clienteDTO equipo = new equipo_clienteDTO();
        equipo.setK_id_equipo_cliente(rs.getString("k_id_equipo_cliente"));
        equipo.setK_serie(rs.getString("k_serie"));
        equipo.setF_fecha_compra(rs.getDate("f_fecha_compra"));
        equipo.setV_valor_compra(rs.getObject("v_valor_compra") != null ? rs.getInt("v_valor_compra") : null);
        equipo.setN_no_inventario(rs.getString("n_no_inventario"));
        equipo.setK_id_area_servicio(rs.getString("k_id_area_servicio"));
        equipo.setK_id_modelo(rs.getString("k_id_modelo"));
        return equipo;
    }
}
