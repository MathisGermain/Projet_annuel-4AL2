package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.model.Response;
import com.esgi.al2.projet.annuel.levelUp.model.User;
import com.esgi.al2.projet.annuel.levelUp.service.ExerciseService;
import com.esgi.al2.projet.annuel.levelUp.service.ResponseService;
import com.esgi.al2.projet.annuel.levelUp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responses")
@AllArgsConstructor
public class ResponseController {
    private final ResponseService responseService;
    private final UserService userService;
    private final ExerciseService exerciseService;


    @PostMapping("/send")
    public Response sendResponse(@RequestBody Response response){
        return responseService.create(response);
    }

    @GetMapping("/user/{user_id}")
    public List<Response> getAllUserResponses(@PathVariable Integer user_id) {
        //Gerer l'exception
        Optional<User> _user = userService.findById(user_id);
        User user = _user.stream().findFirst().get();
        return responseService.findAllByUser(user);
    }

    @GetMapping("/exercise/{exercise_id}")
    public List<Response> getAllExerciseResponses(@PathVariable Integer exercise_id) {
        //Gerer l'exception
        Optional<Exercise> _exercise = exerciseService.findById(exercise_id);
        Exercise exercise = _exercise.stream().findFirst().get();
        return responseService.findAllByExercise(exercise);
    }

    @GetMapping("/{id}")
    public Optional<Response> getResponse(@PathVariable Integer id){
        return responseService.findById(id);
    }

    @GetMapping
    public Optional<Response> getUserResponse(@RequestParam Integer user_id, @RequestParam Integer exercise_id){
        Optional<User> _user = userService.findById(user_id);
        User user = _user.stream().findFirst().get();
        Optional<Exercise> _exercise = exerciseService.findById(exercise_id);
        Exercise exercise = _exercise.stream().findFirst().get();
        return responseService.findByUserAndExercise(user, exercise);
    }

}
