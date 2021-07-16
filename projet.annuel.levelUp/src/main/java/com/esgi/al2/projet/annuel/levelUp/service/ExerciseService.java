package com.esgi.al2.projet.annuel.levelUp.service;

import com.esgi.al2.projet.annuel.levelUp.dto.ExerciseDto;
import com.esgi.al2.projet.annuel.levelUp.exception.EntityNotFoundException;
import com.esgi.al2.projet.annuel.levelUp.exception.ErrorCode;
import com.esgi.al2.projet.annuel.levelUp.exception.InvalidEntityException;
import com.esgi.al2.projet.annuel.levelUp.repository.ExerciseRepository;
import com.esgi.al2.projet.annuel.levelUp.validator.ExerciseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(
            ExerciseRepository exerciseRepository
    ){
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseDto save(ExerciseDto dto){
        List<String> errors = ExerciseValidator.validate(dto);
        if(!errors.isEmpty()) {
            throw new InvalidEntityException("This exercise is not valid", ErrorCode.EXERCISE_NOT_VALID);
        }

        if((exerciseRepository.existsByTitle(dto.getTitle()))){
            throw new InvalidEntityException(
                    "This exercise exists yet",
                    ErrorCode.EXERCISE_ALREADY_EXISTS,
                    Collections.singletonList("This exercise exists yet")
            );
        }
        return ExerciseDto.fromEntity(exerciseRepository.save(
                ExerciseDto.toEntity(dto)
        ));
    }


    public ExerciseDto findById(Integer id) {
        if(id == null) return null;

        return exerciseRepository.findById(id)
                .map(ExerciseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Exercise doesn't exist", ErrorCode.EXERCISE_NOT_FOUND));

    }

    public ExerciseDto findByTitle(String title) {
        return exerciseRepository.findByTitleIgnoreCase(title)
                .map(ExerciseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Exercise doesn't exist", ErrorCode.USER_NOT_FOUND));
    }

    public List<ExerciseDto> findAll() {
        return exerciseRepository.findAll().stream()
                .map(ExerciseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
