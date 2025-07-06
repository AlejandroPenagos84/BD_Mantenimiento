package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fabricante implements Serializable {

    private String k_id_fabricante;
    private String n_nombre;
    private String k_id_pais;
}
