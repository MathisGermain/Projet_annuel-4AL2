package com.esgi.al2.projet.annuel.levelUp.repository;

import com.esgi.al2.projet.annuel.levelUp.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Integer> {

    Optional<Response> findById(Integer id);

    List<Response> findAllByUserId(Integer user_id);

    List<Response> findAllByExerciseId(Integer exercise_id);

    Optional<Response> findByUserIdAndExerciseId(Integer user_id, Integer exercise_id);
}