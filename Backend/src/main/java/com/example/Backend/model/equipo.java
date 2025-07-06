package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipo implements Serializable {

    private String k_id_equipo;
    private String k_id_tipo_equipo;
    private String k_id_marca;

}
