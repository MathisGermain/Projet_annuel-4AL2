package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.dto.ResponseDto;
import com.esgi.al2.projet.annuel.levelUp.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.util.List;

@RestController
@RequestMapping("/api/responses")
@Api("responses")
public class ResponseController {

    private ResponseService responseService;

    @Autowired
    public ResponseController(
            ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping(
            value = "/send",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Save a response",
            notes = "Save a user's response",
            response = ResponseDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Response saved"),
                    @ApiResponse(code = 400, message = "Response invalid")
            }
    )
    public ResponseDto saveResponse(@RequestBody ResponseDto response){
        return responseService.save(response);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Find a response",
            notes = "Find a response by id",
            response = ResponseDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Response found"),
                    @ApiResponse(code = 404, message = "Respopnse not found")
            }
    )
    public ResponseDto findById(@PathVariable Integer id){
        return responseService.findById(id);
    }

    @GetMapping(
            value = "/user/{user_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Return all user's responses",
            responseContainer = "List<ResponseDto>"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user's responses"),
            }
    )
    public List<ResponseDto> findAllByUser(@PathVariable Integer user_id) {
        return responseService.findAllByUserId(user_id);

    }

    @GetMapping(
            value = "/exercise/{exercise_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Return all exercise's responses",
            responseContainer = "List<ResponseDto>"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All exercise's responses"),
            }
    )
    public List<ResponseDto> findAllByExercise(@PathVariable Integer exercise_id) {
        return responseService.findAllByExerciseId(exercise_id);
    }

    @GetMapping(
            value = "/user/user_id/exercise/{exercise_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Return all user's responses for the exercise",
            responseContainer = "List<ResponseDto>"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user's responses for the exercise"),
            }
    )
    public ResponseDto findByUserAndExercise(@PathVariable Integer user_id, @PathVariable Integer exercise_id){
        return responseService.findByUserIdAndExerciseId(user_id, exercise_id);
    }
}
