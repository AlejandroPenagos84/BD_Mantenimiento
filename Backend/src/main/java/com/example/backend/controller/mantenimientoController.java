package com.example.backend.controller;

import com.example.backend.modelDTO.mantenimientoDTO;
import com.example.backend.service.mantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mantenimiento")
@CrossOrigin(origins = "*")
public class mantenimientoController {

    @Autowired
    private mantenimientoService mantenimientoService;

    @GetMapping
    public ResponseEntity<List<mantenimientoDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<mantenimientoDTO> items = mantenimientoService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<mantenimientoDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<mantenimientoDTO> item = mantenimientoService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody mantenimientoDTO mantenimiento) {
        mantenimientoService.save(user, password, mantenimiento);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody mantenimientoDTO mantenimiento) {
        try {
            mantenimientoService.update(user, password, id, mantenimiento);
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
            mantenimientoService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
