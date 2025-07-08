package com.example.backend.controller;

import com.example.backend.modelDTO.correo_personaDTO;
import com.example.backend.service.correo_personaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/correo-persona")
@CrossOrigin(origins = "*")
public class correo_personaController {

    @Autowired
    private correo_personaService correo_personaService;

    @GetMapping
    public ResponseEntity<List<correo_personaDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<correo_personaDTO> items = correo_personaService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<correo_personaDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<correo_personaDTO> item = correo_personaService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody correo_personaDTO correo_persona) {
        correo_personaService.save(user, password, correo_persona);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody correo_personaDTO correo_persona) {
        try {
            correo_personaService.update(user, password, id, correo_persona);
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
            correo_personaService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
