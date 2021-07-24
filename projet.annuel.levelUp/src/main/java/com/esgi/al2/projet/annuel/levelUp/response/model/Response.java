package com.esgi.al2.projet.annuel.levelUp.response.model;

import com.esgi.al2.projet.annuel.levelUp.exercice.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "responses")
public class Response {



    @SequenceGenerator(
            name = "response_sequence",
            sequenceName = "response_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "response_sequence"
    )

    @OneToOne
    private User user;

    @OneToOne
    private Exercise exercise;

    @Id
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(name="codeSent")
    private String codeSent;

    @Column(name="date")
    private LocalDateTime date;


}
