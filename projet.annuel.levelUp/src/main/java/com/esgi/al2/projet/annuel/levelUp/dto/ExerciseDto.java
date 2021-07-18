package com.esgi.al2.projet.annuel.levelUp.dto;

import com.esgi.al2.projet.annuel.levelUp.model.Exercise;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExerciseDto {

    private Integer id;
    private String title;
    private String content;

    public static ExerciseDto fromEntity(Exercise exercise){
        if (exercise == null){
            return null;
        }
        return ExerciseDto.builder()
                .id(exercise.getId())
                .title(exercise.getTitle())
                .content(exercise.getContent())
                .build();
    }

    public static Exercise toEntity(ExerciseDto exerciseDto){
        if (exerciseDto == null){
            return null;
        }
        Exercise exercise = new Exercise();
        exercise.setId(exerciseDto.getId());
        exercise.setTitle(exerciseDto.getTitle());
        exercise.setContent(exerciseDto.getContent());
        return exercise;
    }
}
