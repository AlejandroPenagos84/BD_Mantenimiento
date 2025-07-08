package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class mantenimientoDTO implements Serializable {

    private String k_id_mantenimiento;
    private Date f_fecha_mantenimiento;
    private String n_periodicidad;
    private String k_identificador;

}
