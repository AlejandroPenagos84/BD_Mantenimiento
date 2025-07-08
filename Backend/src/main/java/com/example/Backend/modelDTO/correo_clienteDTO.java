package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class correo_clienteDTO implements Serializable {

    private String n_correo_cliente;
    private String k_id_cliente;
}
