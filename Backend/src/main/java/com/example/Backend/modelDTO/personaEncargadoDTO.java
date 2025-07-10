package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class personaEncargadoDTO implements Serializable {

    private String k_identificador;
    private String k_cedula;
    private String n_primer_nombre;
    private String n_segundo_nombre;
    private String n_primer_apellido;
    private String n_segundo_apellido;
    private String t_tipo_persona;
    private String t_segundo_tipo_persona;
    private String t_tipo_encargado;
    private String k_id_area_servicio;
    private String k_id_sede;
}