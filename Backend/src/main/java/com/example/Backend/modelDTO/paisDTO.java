package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paisDTO implements Serializable {

    private String k_id_pais;
    private String n_nombre_pais;

}
