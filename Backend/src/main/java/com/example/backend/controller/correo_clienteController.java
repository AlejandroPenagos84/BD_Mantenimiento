package com.example.backend.controller;

import com.example.backend.modelDTO.correo_clienteDTO;
import com.example.backend.service.correo_clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/correo-cliente")
@CrossOrigin(origins = "*")
public class correo_clienteController {

    @Autowired
    private correo_clienteService correo_clienteService;

    @GetMapping
    public ResponseEntity<List<correo_clienteDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<correo_clienteDTO> items = correo_clienteService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<correo_clienteDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<correo_clienteDTO> item = correo_clienteService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody correo_clienteDTO correo_cliente) {
        correo_clienteService.save(user, password, correo_cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody correo_clienteDTO correo_cliente) {
        try {
            correo_clienteService.update(user, password, id, correo_cliente);
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
            correo_clienteService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
