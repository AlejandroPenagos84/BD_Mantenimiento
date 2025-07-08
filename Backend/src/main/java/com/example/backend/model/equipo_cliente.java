package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipo_cliente implements Serializable {

    private String k_id_equipo_cliente;
    private String k_serie;
    private Date f_fecha_compra;
    private Integer v_valor_compra;
    private String n_no_inventario;
    private String k_id_area_servicio;
    private String k_id_modelo;

}
