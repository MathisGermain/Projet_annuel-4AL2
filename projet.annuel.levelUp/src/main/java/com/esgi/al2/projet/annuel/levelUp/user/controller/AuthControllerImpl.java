package com.esgi.al2.projet.annuel.levelUp.user.controller;

import com.esgi.al2.projet.annuel.levelUp.user.model.Login;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import com.esgi.al2.projet.annuel.levelUp.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController{
    private final AuthService authService;

    @Override
    public ResponseEntity<Object> create(@RequestBody User user) {
        var uri = authService.registerUser(user);
        return new ResponseEntity<>(ResponseEntity.created(uri).build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Login> login(@RequestBody Login login) {
        var log = new Login(login.getEmail(), login.getPassword());
        HttpHeaders h = authService.createSession(log);
        return new ResponseEntity<>( h , HttpStatus.OK);
    }
}
