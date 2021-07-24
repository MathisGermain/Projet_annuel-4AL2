package com.esgi.al2.projet.annuel.levelUp.comment.controller;


import com.esgi.al2.projet.annuel.levelUp.comment.model.Comment;
import com.esgi.al2.projet.annuel.levelUp.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    public Comment addExercise(@RequestBody Comment exercise) throws Exception {
        return commentService.create(exercise);
    }

    @GetMapping("/response")
    public List<Comment> getAllByResponseId(@RequestBody Integer response_id) {
        return commentService.findAllByresponse_id(response_id);
    }

    @GetMapping("/user")
    public List<Comment> getAllByUserId(@RequestBody Integer user_id) {
        return commentService.findAllByuser_id(user_id);
    }
}
