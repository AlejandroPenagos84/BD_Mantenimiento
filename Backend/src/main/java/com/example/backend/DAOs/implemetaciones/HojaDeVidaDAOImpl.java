package com.example.backend.DAOs.implemetaciones;

import com.example.backend.DAOs.interfaces.HojaDeVidaDAO;
import com.example.backend.modelDTO.HojaDeVidaDTO;
import com.example.backend.modelDTO.verificacion_tecnicaDTO;
import com.example.backend.modelDTO.reporte_servicioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HojaDeVidaDAOImpl implements HojaDeVidaDAO {

    private final Connection conn;

    public HojaDeVidaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<HojaDeVidaDTO> findAllByCliente(String idCliente) throws SQLException {
        String sql = "SELECT * FROM TodoEquipoCliente WHERE k_id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCliente); // 🔧 Se corrige el parámetro

            try (ResultSet rs = stmt.executeQuery()) {
                List<HojaDeVidaDTO> lista = new ArrayList<>();
                while (rs.next()) {
                    HojaDeVidaDTO dto = mapRow(rs);
                    String equipoId = dto.getK_id_equipo_cliente();
                    dto.setVerificacion_tecnica(obtenerVerificacionesTecnicas(equipoId));
                    dto.setReporte_servicio(obtenerReportesServicio(equipoId));
                    lista.add(dto);
                }
                return lista;
            }
        }
    }


    @Override
    public HojaDeVidaDTO findById(String idEquipoCliente) throws SQLException {
        String sql = "SELECT * FROM TodoEquipoCliente WHERE k_id_equipo_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idEquipoCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    HojaDeVidaDTO dto = mapRow(rs);
                    dto.setVerificacion_tecnica(obtenerVerificacionesTecnicas(idEquipoCliente));
                    dto.setReporte_servicio(obtenerReportesServicio(idEquipoCliente));
                    return dto;
                }
            }
        }
        return null;
    }

    private HojaDeVidaDTO mapRow(ResultSet rs) throws SQLException {
        HojaDeVidaDTO dto = new HojaDeVidaDTO();
        dto.setK_id_equipo_cliente(rs.getString("k_id_equipo_cliente"));
        dto.setK_serie(rs.getString("k_serie"));
        dto.setF_fecha_compra(rs.getDate("f_fecha_compra"));
        dto.setV_valor_compra(rs.getDouble("v_valor_compra"));
        dto.setN_no_inventario(rs.getString("n_no_inventario"));
        dto.setK_id_modelo(rs.getString("k_id_modelo"));
        dto.setN_invima(rs.getString("n_invima"));
        dto.setK_id_fabricante(rs.getString("k_id_fabricante"));
        dto.setN_nombre_fabricante(rs.getString("n_nombre_fabricante"));
        dto.setK_id_pais(rs.getString("k_id_pais"));
        dto.setN_nombre_pais(rs.getString("n_nombre_pais"));
        dto.setK_id_marca(rs.getString("k_id_marca"));
        dto.setN_nombre_marca(rs.getString("n_nombre_marca"));
        dto.setK_id_tipo_equipo(rs.getString("k_id_tipo_equipo"));
        dto.setN_nombre_tipo_equipo(rs.getString("n_nombre_tipo_equipo"));
        dto.setT_definicion_tecnica(rs.getString("t_definicion_tecnica"));
        dto.setT_recomendaciones_cuidado(rs.getString("t_recomendaciones_cuidado"));
        dto.setD_amperaje(rs.getString("d_amperaje"));
        dto.setT_tecnologia_predominante(rs.getString("t_tecnologia_predominante"));
        dto.setK_id_area_servicio(rs.getString("k_id_area_servicio"));
        dto.setN_nombre_area(rs.getString("n_nombre_area"));
        dto.setK_id_sede(rs.getString("k_id_sede"));
        dto.setN_nombre_sede(rs.getString("n_nombre_sede"));
        dto.setDir_calle(rs.getString("dir_calle"));
        dto.setDir_carrera(rs.getString("dir_carrera"));
        dto.setDir_num(rs.getString("dir_num"));
        dto.setK_id_ciudad(rs.getString("k_id_ciudad"));
        dto.setN_nombre_ciudad(rs.getString("n_nombre_ciudad"));
        dto.setK_id_cliente(rs.getString("k_id_cliente"));
        dto.setN_tipo_identificacion(rs.getString("n_tipo_identificacion"));
        dto.setN_razon_social(rs.getString("n_razon_social"));
        return dto;
    }

    private List<verificacion_tecnicaDTO> obtenerVerificacionesTecnicas(String idEquipoCliente) throws SQLException {
        String sql = """
            SELECT VERT.*
            FROM verificacion_tecnica VERT
            INNER JOIN verificacion_tecnica_tipo_equipo VTTE ON VERT.k_id_verificacion = VTTE.k_id_verificacion
            INNER JOIN tipo_equipo TE ON VTTE.k_id_tipo_equipo = TE.k_id_tipo_equipo
            INNER JOIN equipo EQ ON TE.k_id_tipo_equipo = EQ.k_id_tipo_equipo
            INNER JOIN modelo MODL ON EQ.k_id_equipo = MODL.k_id_equipo
            INNER JOIN equipo_cliente EC ON MODL.k_id_modelo = EC.k_id_modelo
            WHERE EC.k_id_equipo_cliente = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idEquipoCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                List<verificacion_tecnicaDTO> lista = new ArrayList<>();
                while (rs.next()) {
                    verificacion_tecnicaDTO dto = new verificacion_tecnicaDTO();
                    dto.setK_id_verificacion(rs.getString("k_id_verificacion"));
                    dto.setT_descripcion(rs.getString("t_descripcion"));
                    // Completa con más campos si los tienes
                    lista.add(dto);
                }
                return lista;
            }
        }
    }

    private List<reporte_servicioDTO> obtenerReportesServicio(String idEquipoCliente) throws SQLException {
        String sql = """
            SELECT RS.*
            FROM reporte_servicio RS
            INNER JOIN equipo_cliente EC ON RS.k_id_equipo_cliente = EC.k_id_equipo_cliente
            WHERE EC.k_id_equipo_cliente = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idEquipoCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                List<reporte_servicioDTO> lista = new ArrayList<>();
                while (rs.next()) {
                    reporte_servicioDTO dto = new reporte_servicioDTO();
                    dto.setK_id_cliente(rs.getString("k_id_cliente"));
                    dto.setK_id_mantenimiento(rs.getString("k_id_mantenimineto"));
                    dto.setK_id_equipo_cliente(rs.getString("k_id_equipo_cliente"));
                    dto.setK_id_verificacion_tecnica(rs.getString("k_id_verificacion_tecnica"));
                    dto.setT_observaciones_reporte_servico(rs.getString("t_observaciones_reporte_servico"));
                    dto.setD_valor_equipo(rs.getDouble("d_valor_equipo"));
                    dto.setD_valor_patron(rs.getDouble("d_valor_patron"));
                    dto.setT_resultado_verificacion_tecnica(rs.getString("t_resultado_verificacion_tecnica"));
                    // Completa con más campos si los tienes
                    lista.add(dto);
                }
                return lista;
            }
        }
    }
}
