package com.example.backend.controller;

import com.example.backend.modelDTO.tipo_equipoDTO;
import com.example.backend.service.tipo_equipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-equipo")
@CrossOrigin(origins = "*")
public class tipo_equipoController {

    @Autowired
    private tipo_equipoService tipo_equipoService;

    @GetMapping
    public ResponseEntity<List<tipo_equipoDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<tipo_equipoDTO> items = tipo_equipoService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<tipo_equipoDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<tipo_equipoDTO> item = tipo_equipoService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody tipo_equipoDTO tipo_equipo) {
        tipo_equipoService.save(user, password, tipo_equipo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody tipo_equipoDTO tipo_equipo) {
        try {
            tipo_equipoService.update(user, password, id, tipo_equipo);
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
            tipo_equipoService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
