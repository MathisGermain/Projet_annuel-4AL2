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
@Table(name = "exercises")
public class Exercise extends AbstractEntity{

    private String title;

    private String content;

    @OneToMany(mappedBy = "exercise")
    private List<Response> responses;
}
