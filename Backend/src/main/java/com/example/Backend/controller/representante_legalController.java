package com.example.backend.controller;

import com.example.backend.modelDTO.representante_legalDTO;
import com.example.backend.service.representante_legalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/representante-legal")
@CrossOrigin(origins = "*")
public class representante_legalController {

    @Autowired
    private representante_legalService representante_legalService;

    @GetMapping
    public ResponseEntity<List<representante_legalDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<representante_legalDTO> items = representante_legalService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<representante_legalDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<representante_legalDTO> item = representante_legalService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody representante_legalDTO representante_legal) {
        representante_legalService.save(user, password, representante_legal);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody representante_legalDTO representante_legal) {
        try {
            representante_legalService.update(user, password, id, representante_legal);
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
            representante_legalService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
