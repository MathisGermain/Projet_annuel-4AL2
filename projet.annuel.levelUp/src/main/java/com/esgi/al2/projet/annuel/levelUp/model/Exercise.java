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
@ApiModel(description = "The exercise")
@Table(name = "exercises")
public class Exercise extends AbstractEntity{

    @ApiModelProperty(notes = "The exercise's title")
    private String title;

    @ApiModelProperty(notes = "The exercise's content")
    private String content;

    @ApiModelProperty(notes = "The exercise's reponses")
    @OneToMany(mappedBy = "exercise")
    private List<Response> responses;
}
