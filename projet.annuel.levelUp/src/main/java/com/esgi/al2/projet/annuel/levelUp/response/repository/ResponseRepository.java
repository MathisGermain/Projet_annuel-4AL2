package com.esgi.al2.projet.annuel.levelUp.response.repository;

import com.esgi.al2.projet.annuel.levelUp.exercise.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.response.model.Response;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Integer> {

    List<Response> findAllByUserid(Integer user_id);

    List<Response> findAllByExerciseid(Integer exercise_id);

    Optional<Response> findByUseridAndExerciseid(Integer user_id, Integer exercise_id);

    @NotNull Optional<Response> findById(@NotNull Integer id);

}
