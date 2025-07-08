package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoDTO implements Serializable {

    private String k_id_equipo;
    private String k_id_tipo_equipo;
    private String k_id_marca;

}
