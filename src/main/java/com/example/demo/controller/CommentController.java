package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/getBoard")
    public Optional<Comment> getComment(@RequestParam(name= "commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/postComment")
    public void postComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .build();
        commentService.postComment(comment);
    }

    @PutMapping ("/putComment")
    public void putComment(@RequestBody CommentDTO commentDTO) { commentService.putComment(commentDTO); }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable(name="commentId") Long commentId) { commentService.deleteComment(commentId); }
}
