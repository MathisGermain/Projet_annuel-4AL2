package com.esgi.al2.projet.annuel.levelUp.comment.service;

import com.esgi.al2.projet.annuel.levelUp.comment.model.Comment;
import com.esgi.al2.projet.annuel.levelUp.comment.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    public final CommentRepository commentRepository;

    public Comment create(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findAllByresponse_id(Integer response_id){
        return commentRepository.findAllByResponseid(response_id);
    }

    public List<Comment> findAllByuser_id(Integer user_id){
        return commentRepository.findAllByUserid(user_id);
    }
}
