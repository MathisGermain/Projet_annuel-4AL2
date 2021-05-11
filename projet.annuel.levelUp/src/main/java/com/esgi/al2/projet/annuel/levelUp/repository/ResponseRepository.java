package com.esgi.al2.projet.annuel.levelUp.repository;

import com.esgi.al2.projet.annuel.levelUp.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.model.Response;
import com.esgi.al2.projet.annuel.levelUp.model.User;
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
