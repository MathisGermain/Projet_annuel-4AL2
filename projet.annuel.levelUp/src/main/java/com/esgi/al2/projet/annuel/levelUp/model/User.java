package com.esgi.al2.projet.annuel.levelUp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends AbstractEntity{

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Response> responses;
}
