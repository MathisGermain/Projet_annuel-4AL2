package com.esgi.al2.projet.annuel.levelUp.response.service;


import com.esgi.al2.projet.annuel.levelUp.exercice.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.response.model.Response;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import com.esgi.al2.projet.annuel.levelUp.response.repository.ResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResponseService {
    private final ResponseRepository responseRepository;

    public Response create(Response response) {
        return responseRepository.save(response);
    }

    public Optional<Response> findById(Integer id) {
        return responseRepository.findById(id);
    }

    public List<Response> findAllByUser(User user) {
        return responseRepository.findAllByUser(user);
    }

    public List<Response> findAllByExercise(Exercise exercise) {
        return responseRepository.findAllByExercise(exercise);
    }

    public Optional<Response> findByUserAndExercise(User user, Exercise exercise) {
        return responseRepository.findByUserAndExercise(user, exercise);
    }



}
