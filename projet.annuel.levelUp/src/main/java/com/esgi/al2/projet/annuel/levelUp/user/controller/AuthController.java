package com.esgi.al2.projet.annuel.levelUp.user.controller;

import com.esgi.al2.projet.annuel.levelUp.user.model.Login;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/signup")
    ResponseEntity<Object> create(@RequestBody User user);

    @GetMapping("/token/{id}")
    ResponseEntity<String> getToken(@PathVariable Integer id);

    @PostMapping("/login")
    ResponseEntity<Login> login(@RequestBody Login login);
}

