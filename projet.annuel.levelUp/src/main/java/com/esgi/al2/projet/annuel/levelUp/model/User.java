package com.esgi.al2.projet.annuel.levelUp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "The user")
@Table(name = "users")
public class User extends AbstractEntity{

    @ApiModelProperty(notes = "The user's username")
    private String username;

    @ApiModelProperty(notes = "The user's firstname")
    private String firstname;

    @ApiModelProperty(notes = "The user's lastname")
    private String lastname;

    @ApiModelProperty(notes = "The user's email")
    private String email;

    @ApiModelProperty(notes = "The user's password")
    private String password;

    @ApiModelProperty(notes = "The user's responses")
    @OneToMany(mappedBy = "user")
    private List<Response> responses;
}
