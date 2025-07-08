package com.example.backend.controller;

import com.example.backend.modelDTO.telefono_personaDTO;
import com.example.backend.service.telefono_personaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/telefono-persona")
@CrossOrigin(origins = "*")
public class telefono_personaController {

    @Autowired
    private telefono_personaService telefono_personaService;

    @GetMapping
    public ResponseEntity<List<telefono_personaDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<telefono_personaDTO> items = telefono_personaService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<telefono_personaDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<telefono_personaDTO> item = telefono_personaService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody telefono_personaDTO telefono_persona) {
        telefono_personaService.save(user, password, telefono_persona);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody telefono_personaDTO telefono_persona) {
        try {
            telefono_personaService.update(user, password, id, telefono_persona);
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
            telefono_personaService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
