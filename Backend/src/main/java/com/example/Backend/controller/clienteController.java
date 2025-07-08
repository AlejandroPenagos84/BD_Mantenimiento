package com.example.backend.controller;

import com.example.backend.modelDTO.clienteDTO;
import com.example.backend.service.clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class clienteController {

    @Autowired
    private clienteService clienteService;

    @GetMapping
    public ResponseEntity<List<clienteDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<clienteDTO> items = clienteService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<clienteDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<clienteDTO> item = clienteService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody clienteDTO cliente) {
        clienteService.save(user, password, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody clienteDTO cliente) {
        try {
            clienteService.update(user, password, id, cliente);
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
            clienteService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
