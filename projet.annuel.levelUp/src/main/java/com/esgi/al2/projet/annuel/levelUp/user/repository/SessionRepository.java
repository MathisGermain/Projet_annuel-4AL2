package com.esgi.al2.projet.annuel.levelUp.user.repository;


import com.esgi.al2.projet.annuel.levelUp.user.model.Session;
import com.esgi.al2.projet.annuel.levelUp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByUser(User user);
}
