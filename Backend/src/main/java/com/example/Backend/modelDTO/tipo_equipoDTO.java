package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipo_equipoDTO implements Serializable {

    private String k_id_tipo_equipo;
    private String n_nombre_tipo_equipo;
    private String t_definicion_tecnica;
    private String t_recomendaciones_ciudado;
    private Integer i_voltage;
    private Double d_amperaje;
    private String t_tecnologia_predominante;
    private Boolean b_verificable;
    private Double m_valor_unitario_mantenimiento;
    private String n_tipo_verificacion;
}
