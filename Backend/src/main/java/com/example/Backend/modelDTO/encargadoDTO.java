package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class encargadoDTO implements Serializable {

    private String t_tipo_encargado;
    private String k_identificador;
    private String k_id_area_servicio;
    private String k_id_sede;

}
