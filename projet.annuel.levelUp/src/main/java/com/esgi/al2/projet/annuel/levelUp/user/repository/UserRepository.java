package com.esgi.al2.projet.annuel.levelUp.user.repository;

import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}
