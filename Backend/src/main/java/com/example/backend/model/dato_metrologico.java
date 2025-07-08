package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dato_metrologico implements Serializable {

    private Double d_valor;
    private String n_tipo;
    private Integer k_id_tipo_equipo;
}
