package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class telefono_clienteDTO implements Serializable {

    private String n_numero_cliente;
    private String k_id_cliente;

}
