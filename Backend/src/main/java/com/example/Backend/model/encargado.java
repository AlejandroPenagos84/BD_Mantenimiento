package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class encargado implements Serializable {

    private String t_tipo_encargado;
    private String k_identificador;
    private String k_id_area_servicio;
    private String k_id_sede;

}
