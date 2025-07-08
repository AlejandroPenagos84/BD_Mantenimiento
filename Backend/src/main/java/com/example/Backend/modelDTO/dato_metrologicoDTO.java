package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dato_metrologicoDTO implements Serializable {

    private Double d_valor;
    private String n_tipo;
    private Integer k_id_tipo_equipo;
}
