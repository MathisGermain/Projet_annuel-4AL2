package com.esgi.al2.projet.annuel.levelUp.user.controller;

import com.esgi.al2.projet.annuel.levelUp.user.service.UserService;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/signin")
    public Optional<User> signInUser(@RequestBody String email, @RequestBody String password)
    {
        return userService.connect(email, password);

    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id){return userService.findById(id);}

    @GetMapping()
    public List<User> findAll(){return userService.findAll();}

    @GetMapping("/username/{username}")
    public Optional<User> findByUsername(@PathVariable String username){return userService.findByUsername(username);}

}
