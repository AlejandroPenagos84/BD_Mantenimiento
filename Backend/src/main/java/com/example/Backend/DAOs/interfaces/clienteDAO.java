package com.example.backend.DAOs.interfaces;
import com.example.backend.modelDTO.*;


public interface clienteDAO extends CRUDs<clienteDTO, String> {
    clienteDTO findByRazonSocial(String razon_social);
}
