package com.example.backend.controller;

import com.example.backend.modelDTO.paisDTO;
import com.example.backend.service.paisService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pais")
@CrossOrigin(origins = "*")
public class paisController {

    @Autowired
    private paisService paisService;

    @GetMapping
    public ResponseEntity<List<paisDTO>> getAllItems(HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        List<paisDTO> items = paisService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<paisDTO> getItemById(
            @PathVariable String id, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Optional<paisDTO> item = paisService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestBody paisDTO pais, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        paisService.save(user, password, pais);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestBody paisDTO pais,HttpSession session) {

        try {
            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            paisService.update(user, password, id, pais);
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

            paisService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
