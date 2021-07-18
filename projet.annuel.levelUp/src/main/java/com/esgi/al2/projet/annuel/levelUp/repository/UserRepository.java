package com.esgi.al2.projet.annuel.levelUp.repository;

import com.esgi.al2.projet.annuel.levelUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findById(Integer id);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Optional<User> findByEmailAndPassword(String email, String password);
}