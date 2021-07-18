package com.esgi.al2.projet.annuel.levelUp.comment.repository;

import com.esgi.al2.projet.annuel.levelUp.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


    List<Comment> findAllByResponseid(Integer response_id);
    List<Comment> findAllByUserid(Integer user_id);
}
