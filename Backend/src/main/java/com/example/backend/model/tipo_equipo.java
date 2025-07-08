package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipo_equipo implements Serializable {

    private String k_id_tipo_equipo;
    private String n_nombre_tipo_equipo;
    private String t_definicion_tecnica;
    private String t_recomendaciones_ciudado;
    private Integer i_voltage;
    private Double d_amperaje;
    private String t_tecnologia_predominante;
    private Boolean b_verificable;
    private Integer m_valor_unitario_mantenimiento;
    private String n_tipo_verificacion;
}
