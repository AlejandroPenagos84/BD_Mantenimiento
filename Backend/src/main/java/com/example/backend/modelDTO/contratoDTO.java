package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class contratoDTO implements Serializable {
    private String k_id_contrato;
    private String t_estado_contrato;
    private Date f_fecha_inicio;
    private Date f_fecha_finalizacion;
    private String t_observaciones;
}
