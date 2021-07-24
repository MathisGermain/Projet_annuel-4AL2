package com.esgi.al2.projet.annuel.levelUp.exercice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "exercises")
public class Exercise {

    @SequenceGenerator(
            name = "exercise_sequence",
            sequenceName = "exercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercise_sequence"
    )

    @Id
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ElementCollection
    @CollectionTable(name="test", joinColumns=@JoinColumn(name="exercise_id"))
    @Column(name = "test_exercise")
    private List<String> test;

}
