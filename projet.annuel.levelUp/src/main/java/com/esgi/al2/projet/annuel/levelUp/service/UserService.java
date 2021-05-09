package com.esgi.al2.projet.annuel.levelUp.service;

import com.esgi.al2.projet.annuel.levelUp.model.User;
import com.esgi.al2.projet.annuel.levelUp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) throws Exception {
        if(!(userRepository.existsByEmail(user.getEmail()) && userRepository.existsByUsername(user.getUsername()))){
            return userRepository.save(user);
        }
        throw new Exception("This email is already used by an other user");
    }

}
