package com.example.backend.controller;

import com.example.backend.modelDTO.loginDTO;
import com.example.backend.modelDTO.registerDTO;
import com.example.backend.service.LoginService;
import com.example.backend.service.registerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class sessionController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private registerService registerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody loginDTO loginRequest,
                                        HttpSession session) {
        String user = loginRequest.getUser();
        String password = loginRequest.getPassword();

        if (loginService.validateUser(user, password)) {
            session.setAttribute("user", user);
            session.setAttribute("password", password);
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody registerDTO registerRequest,
                                           HttpSession session) {
        String user = (String) session.getAttribute("user");
        String password = (String) session.getAttribute("password");

        if (loginService.validateUser(user, password)) {
            registerService.register(registerRequest,user,password);
            return ResponseEntity.ok("Registro exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
