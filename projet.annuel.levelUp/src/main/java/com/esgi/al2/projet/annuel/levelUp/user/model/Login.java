package com.esgi.al2.projet.annuel.levelUp.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    private final String email;
    private final String password;
}
