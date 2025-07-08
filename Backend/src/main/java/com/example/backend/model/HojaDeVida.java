package com.example.backend.model;
import com.example.backend.modelDTO.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HojaDeVida implements Serializable {
    // Columnas de la vista TodoEquipoCliente
    private String k_id_equipo_cliente;
    private String k_serie;
    private Date f_fecha_compra;
    private Double v_valor_compra;
    private String n_no_inventario;
    private String k_id_modelo;
    private String n_invima;
    private String k_id_fabricante;
    private String n_nombre_fabricante;
    private String k_id_pais;
    private String n_nombre_pais;
    private String k_id_marca;
    private String n_nombre_marca;
    private String k_id_tipo_equipo;
    private String n_nombre_tipo_equipo;
    private String t_definicion_tecnica;
    private String t_recomendaciones_cuidado;
    private String d_amperaje;
    private String t_tecnologia_predominante;
    private String k_id_area_servicio;
    private String n_nombre_area;
    private String k_id_sede;
    private String n_nombre_sede;
    private String dir_calle;
    private String dir_carrera;
    private String dir_num;
    private String k_id_ciudad;
    private String n_nombre_ciudad;
    private String k_id_cliente;
    private String n_tipo_identificacion;
    private String n_razon_social;

    // Relaciones uno a muchos
    private List<verificacion_tecnicaDTO> verificacion_tecnica;
    private List<reporte_servicioDTO> reporte_servicio;
}
