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
        throw new Exception("This user exists yet");
    }

    public Optional<User> connect(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        return user;
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
