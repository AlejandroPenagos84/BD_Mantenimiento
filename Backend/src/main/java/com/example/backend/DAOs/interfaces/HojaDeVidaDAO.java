package com.example.backend.DAOs.interfaces;
import com.example.backend.modelDTO.HojaDeVidaDTO;

import java.sql.SQLException;
import java.util.List;

public interface HojaDeVidaDAO{
    List<HojaDeVidaDTO> findAllByCliente(String id_cliente) throws SQLException;
    HojaDeVidaDTO findById(String idEquipoCliente) throws SQLException;
}
