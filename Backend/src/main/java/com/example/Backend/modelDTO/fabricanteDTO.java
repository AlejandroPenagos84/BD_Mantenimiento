package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fabricanteDTO implements Serializable {

    private String k_id_fabricante;
    private String n_nombre;
    private String k_id_pais;
}
