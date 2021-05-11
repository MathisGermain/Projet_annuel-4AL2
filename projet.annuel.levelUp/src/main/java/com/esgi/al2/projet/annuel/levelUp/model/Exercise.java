package com.esgi.al2.projet.annuel.levelUp.model;

import javax.persistence.*;
import java.util.List;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTest() {
        return test;
    }

    public void setTest(List<String> test) {
        this.test = test;
    }
}
