package com.esgi.al2.projet.annuel.levelUp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@ApiModel(description = "The returned response")
@Table(name = "responses")
public class Response extends AbstractEntity{

    @ApiModelProperty(notes = "Author of the response")
    @ManyToOne
    private User user;

    @ApiModelProperty(notes = "Exercise solved")
    @ManyToOne
    private Exercise exercise;

    @ApiModelProperty(notes = "Code sent by the user")
    private String codeSent;

    @ApiModelProperty(notes = "The output of the program during the execution")
    private String output;

    @ApiModelProperty(notes = "The expected output")
    private String expectedOutput;

    @ApiModelProperty(notes = "The verdict can be one of these : Accepted, Wrong Answer, Compilation Error, Runtime Error, Out Of Memory, Time Limit Exceeded")
    private String status;

    @ApiModelProperty(notes = "Result of the code sent compilation")
    @OneToOne
    private Result result;
}
