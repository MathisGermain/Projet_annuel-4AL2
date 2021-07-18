package com.esgi.al2.projet.annuel.levelUp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@ApiModel(description = "The result of the execution")
@Table(name = "results")
public class Result extends AbstractEntity{

    @ApiModelProperty(notes = "The verdict can be one of these : Accepted, Wrong Answer, Compilation Error, Runtime Error, Out Of Memory, Time Limit Exceeded")
    private String verdict;

    @ApiModelProperty(notes = "The output of the program during the execution")
    private String output;

    @ApiModelProperty(notes = "The expected output")
    private String expectedOutput;

    @ApiModelProperty(notes = "The response that generated this result")
    @OneToOne
    private Response response;
}
