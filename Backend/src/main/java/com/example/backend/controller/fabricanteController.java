package com.example.backend.controller;

import com.example.backend.modelDTO.fabricanteDTO;
import com.example.backend.service.fabricanteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fabricante")
@CrossOrigin(origins = "*")
public class fabricanteController {

    @Autowired
    private fabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<List<fabricanteDTO>> getAllItems(HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        List<fabricanteDTO> items = fabricanteService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<fabricanteDTO> getItemById(
            @PathVariable String id,
            HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Optional<fabricanteDTO> item = fabricanteService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestBody fabricanteDTO fabricante, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        fabricanteService.save(user, password, fabricante);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestBody fabricanteDTO fabricante, HttpSession session) {
        try {
            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            fabricanteService.update(user, password, id, fabricante);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable String id, HttpSession session) {
        try {
            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            fabricanteService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
