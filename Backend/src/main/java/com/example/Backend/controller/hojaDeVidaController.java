package com.example.backend.controller;

import com.example.backend.modelDTO.fabricanteDTO;
import com.example.backend.modelDTO.hojaDeVidaDTO;
import com.example.backend.service.fabricanteService;
import com.example.backend.service.hojaDeVidaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hojaDeVida")
@CrossOrigin(origins = "*")
public class hojaDeVidaController {
    @Autowired
    private hojaDeVidaService hojaDeVidaService;

    @GetMapping("/cliente")
    public ResponseEntity<List<hojaDeVidaDTO>> findByCliente(
            @RequestParam String idCliente, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        List<hojaDeVidaDTO> items = hojaDeVidaService.findByCliente(user, password, idCliente);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<hojaDeVidaDTO> findByIdTeam(
            @PathVariable String idEquipo, HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        Optional<hojaDeVidaDTO> item = hojaDeVidaService.findById(user, password, idEquipo);
        return item.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
