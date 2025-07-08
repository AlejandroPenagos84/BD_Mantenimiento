package com.example.backend.controller;

import com.example.backend.modelDTO.telefono_clienteDTO;
import com.example.backend.service.telefono_clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/telefono-cliente")
@CrossOrigin(origins = "*")
public class telefono_clienteController {

    @Autowired
    private telefono_clienteService telefono_clienteService;

    @GetMapping
    public ResponseEntity<List<telefono_clienteDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<telefono_clienteDTO> items = telefono_clienteService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<telefono_clienteDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<telefono_clienteDTO> item = telefono_clienteService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody telefono_clienteDTO telefono_cliente) {
        telefono_clienteService.save(user, password, telefono_cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody telefono_clienteDTO telefono_cliente) {
        try {
            telefono_clienteService.update(user, password, id, telefono_cliente);
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
            telefono_clienteService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
