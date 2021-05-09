package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.model.User;
import com.esgi.al2.projet.annuel.levelUp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}
