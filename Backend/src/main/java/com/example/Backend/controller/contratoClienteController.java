package com.example.backend.controller;

import com.example.backend.modelDTO.contratoClienteDTO;
import com.example.backend.modelDTO.contratoDTO;
import com.example.backend.service.contratoClienteService;
import com.example.backend.service.contratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contratoCliente")
@CrossOrigin(origins = "*")
public class contratoClienteController {

    @Autowired
    private contratoClienteService contratoService;

    @GetMapping
    public ResponseEntity<List<contratoClienteDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<contratoClienteDTO> items = contratoService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{idContrato}/{idEmpleado}")
    public ResponseEntity<contratoClienteDTO> getItemByIds(
            @PathVariable String idContrato,
            @PathVariable String idEmpleado,
            @RequestParam String user,
            @RequestParam String password) {

        Optional<contratoClienteDTO> item = contratoService.getById(user, password, idContrato, idEmpleado);
        return item.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
