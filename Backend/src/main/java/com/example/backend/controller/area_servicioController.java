package com.example.backend.controller;

import com.example.backend.modelDTO.area_servicioDTO;
import com.example.backend.service.area_servicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/area-servicio")
@CrossOrigin(origins = "*")
public class area_servicioController {

    @Autowired
    private area_servicioService area_servicioService;

    @GetMapping
    public ResponseEntity<List<area_servicioDTO>> getAllItems(
            @RequestParam String user,
            @RequestParam String password) {
        List<area_servicioDTO> items = area_servicioService.getAll(user, password);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<area_servicioDTO> getItemById(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password) {
        Optional<area_servicioDTO> item = area_servicioService.getById(user, password, id);
        return item.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody area_servicioDTO area_servicio) {
        area_servicioService.save(user, password, area_servicio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(
            @PathVariable String id,
            @RequestParam String user,
            @RequestParam String password,
            @RequestBody area_servicioDTO area_servicio) {
        try {
            area_servicioService.update(user, password, id, area_servicio);
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
            area_servicioService.deleteById(user, password, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
