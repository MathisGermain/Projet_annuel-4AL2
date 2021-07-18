package com.esgi.al2.projet.annuel.levelUp.user.controller;

import com.esgi.al2.projet.annuel.levelUp.user.model.Login;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/signup")
    ResponseEntity<Object> create(@RequestBody User user);

    @PostMapping("/login")
    ResponseEntity<Login> login(@RequestBody Login login);
}

