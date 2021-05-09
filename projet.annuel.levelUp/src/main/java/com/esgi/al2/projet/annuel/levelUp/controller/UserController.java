package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.model.User;
import com.esgi.al2.projet.annuel.levelUp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) throws Exception {
        return userService.create(user);
    }

    @PostMapping("/signin")
    public void signInUser(User user)
    {

    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable String id){return userService.findById(id);}

    @GetMapping("/username/{username}")
    public Optional<User> findByUsername(@PathVariable String username){return userService.findByUsername(username);}

}
