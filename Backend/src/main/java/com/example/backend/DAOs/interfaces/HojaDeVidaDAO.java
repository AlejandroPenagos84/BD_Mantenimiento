package com.example.backend.DAOs.interfaces;
import com.example.backend.modelDTO.hojaDeVidaDTO;

import java.sql.SQLException;
import java.util.List;

public interface hojaDeVidaDAO{
    List<hojaDeVidaDTO> findAllByCliente(String id_cliente) throws SQLException;
    hojaDeVidaDTO findById(String idEquipoCliente) throws SQLException;
}