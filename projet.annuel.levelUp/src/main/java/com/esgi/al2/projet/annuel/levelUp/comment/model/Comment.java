package com.esgi.al2.projet.annuel.levelUp.comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {

        @SequenceGenerator(
                name = "exercise_sequence",
                sequenceName = "comment_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "comment_sequence"
        )
        @Id
        @Column(updatable = false, nullable = false)
        private Integer id;

        @Column(name="response_id")
        private Integer responseid;

        @Column(name="user_id")
        private Integer userid;

        @Column(name="content")
        private String content;

        @Column(name="date")
        private Date date;

}
