package com.esgi.al2.projet.annuel.levelUp.user.service;

import com.esgi.al2.projet.annuel.levelUp.user.model.Session;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;

import com.esgi.al2.projet.annuel.levelUp.user.repository.SessionRepository;
import com.esgi.al2.projet.annuel.levelUp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByEmail(username).get();
    }

    public Optional<Session> findById(User user) {
        return sessionRepository.findByUser(user);
    }
}
