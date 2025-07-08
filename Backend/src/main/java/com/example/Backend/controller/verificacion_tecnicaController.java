package com.example.backend.controller;

import com.example.backend.modelDTO.verificacion_tecnicaDTO;
import com.example.backend.service.verificacion_tecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/verificacion-tecnica")
@CrossOrigin(origins = "*")
public class verificacion_tecnicaController {

    @Autowired
    private verificacion_tecnicaService verificacion_tecnicaService;

    @GetMapping
    public ResponseEntity<List<verificacion_tecnicaDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<verificacion_tecnicaDTO> items = verificacion_tecnicaService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<verificacion_tecnicaDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<verificacion_tecnicaDTO> item = verificacion_tecnicaService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody verificacion_tecnicaDTO verificacion_tecnica) {
        verificacion_tecnicaService.save(user, password, verificacion_tecnica);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody verificacion_tecnicaDTO verificacion_tecnica) {
        try {
            verificacion_tecnicaService.update(user, password, id, verificacion_tecnica);
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
            verificacion_tecnicaService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
