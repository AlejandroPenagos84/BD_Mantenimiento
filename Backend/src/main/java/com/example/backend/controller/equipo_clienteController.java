package com.example.backend.controller;

import com.example.backend.modelDTO.equipo_clienteDTO;
import com.example.backend.service.equipo_clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipo-cliente")
@CrossOrigin(origins = "*")
public class equipo_clienteController {

    @Autowired
    private equipo_clienteService equipo_clienteService;

    @GetMapping
    public ResponseEntity<List<equipo_clienteDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<equipo_clienteDTO> items = equipo_clienteService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<equipo_clienteDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<equipo_clienteDTO> item = equipo_clienteService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody equipo_clienteDTO equipo_cliente) {
        equipo_clienteService.save(user, password, equipo_cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody equipo_clienteDTO equipo_cliente) {
        try {
            equipo_clienteService.update(user, password, id, equipo_cliente);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        try {
            equipo_clienteService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
