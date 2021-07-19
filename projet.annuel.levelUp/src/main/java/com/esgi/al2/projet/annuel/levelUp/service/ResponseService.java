package com.esgi.al2.projet.annuel.levelUp.service;

import com.esgi.al2.projet.annuel.levelUp.dto.ResponseDto;
import com.esgi.al2.projet.annuel.levelUp.exception.EntityNotFoundException;
import com.esgi.al2.projet.annuel.levelUp.exception.ErrorCode;
import com.esgi.al2.projet.annuel.levelUp.exception.InvalidEntityException;
import com.esgi.al2.projet.annuel.levelUp.repository.ResponseRepository;
import com.esgi.al2.projet.annuel.levelUp.validator.ResponseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    private ResponseRepository responseRepository;

    @Autowired
    public ResponseService(
            ResponseRepository responseRepository
    ){
        this.responseRepository = responseRepository;
    }

    public ResponseDto save(ResponseDto response) {
        List<String> errors = ResponseValidator.validate(response);
        if(!errors.isEmpty()) {
            throw new InvalidEntityException("This response is not valid", ErrorCode.RESPONSE_NOT_VALID);
        }

        return ResponseDto.fromEntity(responseRepository.save(
                ResponseDto.toEntity(response)
        ));

    }

    public ResponseDto findById(Integer id) {
        if(id == null) return null;

        return responseRepository.findById(id)
                .map(ResponseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Response doesn't exist" , ErrorCode.RESPONSE_NOT_FOUND));
    }

    public List<ResponseDto> findAllByUserId(Integer user_id) {
        if(user_id == null) return null;

        return responseRepository.findAllByUserId(user_id).stream()
                .map(ResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ResponseDto> findAllByExerciseId(Integer exercise_id) {
        if(exercise_id == null) return null;

        return responseRepository.findAllByExerciseId(exercise_id).stream()
                .map(ResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ResponseDto findByUserIdAndExerciseId(Integer user_id, Integer exercise_id) {
        if(user_id == null || exercise_id == null) return null;

        return responseRepository.findByUserIdAndExerciseId(user_id, exercise_id)
                .map(ResponseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Response doesn't exist" , ErrorCode.RESPONSE_NOT_FOUND));
    }
}
