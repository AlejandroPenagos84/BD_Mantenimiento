package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sede implements Serializable {

    private String k_id_sede;
    private String n_nombre_sede;
    private String dir_calle;
    private String dir_carrera;
    private String dir_num;
    private String k_id_cliente;
    private String k_id_ciudad;

}
