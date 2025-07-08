package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class area_servicioDTO implements Serializable {

    private String k_id_area_servicio;
    private String n_nombre_area;
    private String k_id_sede;
}
