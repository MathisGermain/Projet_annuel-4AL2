package com.esgi.al2.projet.annuel.levelUp.validator;

import com.esgi.al2.projet.annuel.levelUp.dto.ExerciseDto;

import java.util.ArrayList;
import java.util.List;

public class ExerciseValidator {

    public static List<String> validate (ExerciseDto exerciseDto){
        List <String> errors = new ArrayList<>();

        if(exerciseDto.getTitle() == null){
            errors.add("Veuillez renseigner le titre de l'exercice");
        }

        if(exerciseDto.getContent() == null){
            errors.add("Veuillez renseigner le contenu de l'exercice");
        }

        return errors;
    }
}
