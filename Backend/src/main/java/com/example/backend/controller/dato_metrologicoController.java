package com.example.backend.controller;

import com.example.backend.modelDTO.dato_metrologicoDTO;
import com.example.backend.service.dato_metrologicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dato-metrologico")
@CrossOrigin(origins = "*")
public class dato_metrologicoController {

    @Autowired
    private dato_metrologicoService dato_metrologicoService;

    @GetMapping
    public ResponseEntity<List<dato_metrologicoDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<dato_metrologicoDTO> items = dato_metrologicoService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<dato_metrologicoDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<dato_metrologicoDTO> item = dato_metrologicoService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody dato_metrologicoDTO dato_metrologico) {
        dato_metrologicoService.save(user, password, dato_metrologico);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody dato_metrologicoDTO dato_metrologico) {
        try {
            dato_metrologicoService.update(user, password, id, dato_metrologico);
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
            dato_metrologicoService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
