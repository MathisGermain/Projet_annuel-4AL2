package com.esgi.al2.projet.annuel.levelUp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeSent() {
        return codeSent;
    }

    public void setCodeSent(String codeSent) {
        this.codeSent = codeSent;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
