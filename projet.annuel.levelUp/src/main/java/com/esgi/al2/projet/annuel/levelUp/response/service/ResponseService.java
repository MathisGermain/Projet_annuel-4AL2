package com.esgi.al2.projet.annuel.levelUp.response.service;


import com.esgi.al2.projet.annuel.levelUp.response.model.Response;
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
        // check exist if()
            responseRepository.delete(response);
        return responseRepository.save(response);
    }

    public Optional<Response> findById(Integer id) {
        return responseRepository.findById(id);
    }

    public List<Response> findAllByUser(Integer user) {
        return responseRepository.findAllByUserid(user);
    }

    public List<Response> findAllByExercise(Integer exercise) {
        return responseRepository.findAllByExerciseid(exercise);
    }

    public Optional<Response> findByUserAndExercise(Integer user_id, Integer exercise_id) {
        return responseRepository.findByUseridAndExerciseid(user_id, exercise_id);
    }
}
