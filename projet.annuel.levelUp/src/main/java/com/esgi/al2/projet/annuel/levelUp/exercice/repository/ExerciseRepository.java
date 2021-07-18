package com.esgi.al2.projet.annuel.levelUp.exercice.repository;

import com.esgi.al2.projet.annuel.levelUp.exercice.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    Optional<Exercise> findByTitle(String title);

    Optional<Exercise> findById(Integer id);

    Boolean existsByTitle(String title);

}
