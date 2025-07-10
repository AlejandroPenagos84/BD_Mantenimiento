package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class representante_legalDTO implements Serializable {

    private String k_identificador;
    private String k_id_cliente;
}
