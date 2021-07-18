package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.dto.UserDto;
import com.esgi.al2.projet.annuel.levelUp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Api("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            value =  "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Create an user",
            notes = "Create an user with username, email, firstname, lastname and password",
            response = UserDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "User created"),
                    @ApiResponse(code = 400, message = "User invalid")
            }
    )
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.create(user);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Find an user",
            notes = "Find an user by id",
            response = UserDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "User found"),
                    @ApiResponse(code = 404, message = "User not found")
            }
    )
    public UserDto findById(@PathVariable Integer id) {return userService.findById(id);}

    @GetMapping(
            value = "/find/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Find an user",
            notes = "Find an user by username",
            response = UserDto.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "User found"),
                    @ApiResponse(code = 404, message = "User not found")
            }
    )
    public UserDto findByUsername(@PathVariable String username) {return userService.findByUsername(username);}

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Return all users",
            responseContainer = "List<UserDto>"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All users"),
            }
    )
    public List<UserDto> findAll() {return userService.findAll();}
}
