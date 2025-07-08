package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class clienteDTO implements Serializable {

    private String k_id_cliente;
    private String n_tipo_identificacion;
    private String n_razon_social;
    private String n_tipo_cliente;
    private String k_id_pais;
}
