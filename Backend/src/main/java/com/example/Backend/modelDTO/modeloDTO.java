package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class modeloDTO implements Serializable {

    private String k_id_modelo;
    private String n_invima;
    private String k_id_fabricante;
    private String k_id_equipo;

}
