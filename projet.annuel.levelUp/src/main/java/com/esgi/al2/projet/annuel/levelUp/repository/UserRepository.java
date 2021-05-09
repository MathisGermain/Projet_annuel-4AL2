package com.esgi.al2.projet.annuel.levelUp.repository;

import com.esgi.al2.projet.annuel.levelUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<Object> findByUsername(String username);
    Optional<Object> findById(Long id);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);


}
