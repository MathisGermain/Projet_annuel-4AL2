package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.dto.ExerciseDto;
import com.esgi.al2.projet.annuel.levelUp.service.ExerciseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@Api("exercises")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping(
            value ="/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Save an user",
            notes = "Save an user with title and content",
            response = ExerciseDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Exercise created"),
                    @ApiResponse(code = 400, message = "Exercise invalid")
            }
    )
    public ExerciseDto saveExercise(@RequestBody ExerciseDto exercise){
        return exerciseService.save(exercise);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Find an exercise",
            notes = "Find an exercise by id",
            response = ExerciseDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Exercise found"),
                    @ApiResponse(code = 404, message = "Exercise not found")
            }
    )
    public ExerciseDto findById(@PathVariable Integer id){return exerciseService.findById(id); }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Return all exercises",
            responseContainer = "List<ExerciseDto>"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All exercises"),
            }
    )
    public List<ExerciseDto> findAll() {
        return exerciseService.findAll();
    }
}