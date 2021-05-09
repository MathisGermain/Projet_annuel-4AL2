package com.esgi.al2.projet.annuel.levelUp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
@Entity (name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    @NotNull(message = "First name is required")
    private String username;

    @Column
    @NotNull(message = "First name is required")
    private String firstName;

    @Column
    @NotNull(message = "last name is required")
    private String lastName;

    @Column
    @NotNull(message = "password is required")
    private String password;

    @Column
    @NotNull(message = "email is required")
    private String email;
}
