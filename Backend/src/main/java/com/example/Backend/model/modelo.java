package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class modelo implements Serializable {

    private String k_id_modelo;
    private String n_invima;
    private String k_id_fabricante;
    private String k_id_equipo;

}
