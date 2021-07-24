package com.esgi.al2.projet.annuel.levelUp.response.repository;

import com.esgi.al2.projet.annuel.levelUp.exercice.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.response.model.Response;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Integer> {

    List<Response> findAllByUser(User user);

    List<Response> findAllByExercise(Exercise exercise);

    Optional<Response> findByUserAndExercise(User user, Exercise exercise);

    @NotNull Optional<Response> findById(@NotNull Integer id);

}
