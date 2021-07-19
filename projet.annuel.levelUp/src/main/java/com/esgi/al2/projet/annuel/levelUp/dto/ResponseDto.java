package com.esgi.al2.projet.annuel.levelUp.dto;

import com.esgi.al2.projet.annuel.levelUp.model.Response;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto {

    private Integer id;
    private String codeSent;
    private UserDto user;
    private ExerciseDto exercise;

    public static ResponseDto fromEntity(Response response){
        if (response == null){
            return null;
        }
        return ResponseDto.builder()
                .id(response.getId())
                .codeSent(response.getCodeSent())
                .user(UserDto.fromEntity(response.getUser()))
                .exercise(ExerciseDto.fromEntity(response.getExercise()))
                .build();
    }

    public static Response toEntity(ResponseDto responseDto){
        if (responseDto == null){
            return null;
        }
        Response response = new Response();
        response.setId(responseDto.getId());
        response.setCodeSent(responseDto.getCodeSent());
        response.setUser(UserDto.toEntity(responseDto.getUser()));
        response.setExercise(ExerciseDto.toEntity(responseDto.getExercise()));
        return response;
    }
}
