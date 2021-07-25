package com.esgi.al2.projet.annuel.levelUp.response.model;

import com.esgi.al2.projet.annuel.levelUp.exercise.model.Exercise;
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
    @Id
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(name="user_id")
    private Integer userid;

    @Column(name="exercise_id")
    private Integer exerciseid;

    @Column(name="codeSent")
    private String codeSent;

    @Column(name="status")
    private String status;

    @Column(name="date")
    private LocalDateTime date;

}
