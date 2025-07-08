package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cotizacionDTO implements Serializable {

    private String k_id_equipo_cliente;
    private String k_id_contrato;
    private Double m_valor_unitario_mantenimiento;
    private Double m_valor_total;
    private String t_observacion;
    private Date f_fecha_emision;
    private Date f_validez;
    private String t_estado_cotizacion;

}
