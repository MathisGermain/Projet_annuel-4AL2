package com.esgi.al2.projet.annuel.levelUp.validator;

import com.esgi.al2.projet.annuel.levelUp.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate (UserDto userDto){
        List <String> errors = new ArrayList<>();

        if(userDto.getUsername() == null){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }

        if(userDto.getPassword() == null){
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }

        return errors;
    }
}
