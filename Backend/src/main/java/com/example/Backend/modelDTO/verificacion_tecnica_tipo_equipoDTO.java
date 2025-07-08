package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class verificacion_tecnica_tipo_equipoDTO implements Serializable {

    private String k_id_verificacion;
    private String k_id_tipo_equipo;
}
