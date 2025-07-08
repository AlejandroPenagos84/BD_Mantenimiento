package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class reporte_servicioDTO implements Serializable {
    private String k_id_mantenimiento;
    private String k_id_equipo_cliente;
    private String k_id_cliente;
    private String k_id_verificacion_tecnica;
    private String t_observaciones_reporte_servico;
    private Double d_valor_equipo;
    private Double d_valor_patron;
    private String t_resultado_verificacion_tecnica;



}
