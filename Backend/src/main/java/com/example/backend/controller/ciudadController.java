package com.example.backend.controller;

import com.example.backend.modelDTO.ciudadDTO;
import com.example.backend.service.ciudadService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ciudad")
@CrossOrigin(origins = "*")
public class ciudadController {

    @Autowired
    private ciudadService ciudadService;

    @GetMapping
    public ResponseEntity<List<ciudadDTO>> getAllItems(HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        List<ciudadDTO> items = ciudadService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ciudadDTO> getItemById(
            @PathVariable String id, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Optional<ciudadDTO> item = ciudadService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestBody ciudadDTO ciudad, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        ciudadService.save(user, password, ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestBody ciudadDTO ciudad, HttpSession session) {
        try {
            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            ciudadService.update(user, password, id, ciudad);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable String id,
            HttpSession session) {
        try {
            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            ciudadService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
