package com.example.backend.DAOs.interfaces;

import com.example.backend.modelDTO.contratoClienteDTO;

import java.util.List;

public interface contratoClienteDAO {
    contratoClienteDTO findById(String k_id_contrato, String k_id_cliente);
    List<contratoClienteDTO> findAll();
}
