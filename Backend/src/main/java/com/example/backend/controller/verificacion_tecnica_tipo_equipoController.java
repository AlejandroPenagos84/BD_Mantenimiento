package com.example.backend.controller;

import com.example.backend.modelDTO.verificacion_tecnica_tipo_equipoDTO;
import com.example.backend.service.verificacion_tecnica_tipo_equipoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/verificacion-tecnica-tipo-equipo")
@CrossOrigin(origins = "*")
public class verificacion_tecnica_tipo_equipoController {

    @Autowired
    private verificacion_tecnica_tipo_equipoService verificacion_tecnica_tipo_equipoService;

    @GetMapping
    public ResponseEntity<List<verificacion_tecnica_tipo_equipoDTO>> getAllItems(HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        List<verificacion_tecnica_tipo_equipoDTO> items =
                verificacion_tecnica_tipo_equipoService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<verificacion_tecnica_tipo_equipoDTO> getItemById(
            @PathVariable String id, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Optional<verificacion_tecnica_tipo_equipoDTO> item = verificacion_tecnica_tipo_equipoService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestBody verificacion_tecnica_tipo_equipoDTO verificacion_tecnica_tipo_equipo, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        verificacion_tecnica_tipo_equipoService.save(user, password, verificacion_tecnica_tipo_equipo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestBody verificacion_tecnica_tipo_equipoDTO verificacion_tecnica_tipo_equipo, HttpSession session) {
        try {
            String user = (String) session.getAttribute("user");
            String password = (String) session.getAttribute("password");

            verificacion_tecnica_tipo_equipoService.update(user, password, id, verificacion_tecnica_tipo_equipo);
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

            verificacion_tecnica_tipo_equipoService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
