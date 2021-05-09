package com.esgi.al2.projet.annuel.levelUp.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity (name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    private String username;

    @NotNull()
    private String firstName;

    @NotNull()
    private String lastName;

    @NotNull()
    private String password;

    @NotNull()
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

}
