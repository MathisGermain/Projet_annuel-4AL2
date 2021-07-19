package com.esgi.al2.projet.annuel.levelUp.validator;

import com.esgi.al2.projet.annuel.levelUp.dto.ResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ResponseValidator {

    public static List<String> validate (ResponseDto responseDto){
        List <String> errors = new ArrayList<>();

        if(responseDto.getCodeSent() == null){
            errors.add("Veuillez renseigner le code");
        }

        if(responseDto.getUser() == null){
            errors.add("Veuillez renseigner l'auteur de la reponse'");
        }

        if(responseDto.getExercise() == null){
            errors.add("Veuillez renseigner l'exercice");
        }

        return errors;
    }
}
