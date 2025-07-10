package com.example.backend.controller;

import com.example.backend.modelDTO.cotizacionDTO;
import com.example.backend.service.cotizacionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cotizacion")
@CrossOrigin(origins = "*")
public class cotizacionController {

    @Autowired
    private cotizacionService cotizacionService;

    @GetMapping
    public ResponseEntity<List<cotizacionDTO>> getAllItems(HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        List<cotizacionDTO> items = cotizacionService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<cotizacionDTO> getItemById(
            @PathVariable String id,HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Optional<cotizacionDTO> item = cotizacionService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestBody cotizacionDTO cotizacion,HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        cotizacionService.save(user, password, cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestBody cotizacionDTO cotizacion, HttpSession session) {
        try {

            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            cotizacionService.update(user, password, id, cotizacion);
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

            cotizacionService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
