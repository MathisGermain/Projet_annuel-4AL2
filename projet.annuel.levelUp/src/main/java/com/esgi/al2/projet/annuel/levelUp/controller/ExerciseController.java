package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping("/add")
    public Exercise addExercise(@RequestBody Exercise exercise) throws Exception {
        return exerciseService.create(exercise);
    }

    @GetMapping
    public List<Exercise> getAll() {
        return exerciseService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Exercise> findById(@PathVariable Integer id){return exerciseService.findById(id); }

}
