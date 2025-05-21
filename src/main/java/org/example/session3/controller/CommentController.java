package org.example.session3.controller;

import lombok.RequiredArgsConstructor;
import org.example.session3.dto.CommentDTO;
import org.example.session3.entitiy.Comment;
import org.example.session3.service.CommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getComment")
    public Optional<Comment> getComment(@RequestParam(name = "commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/postComment")
    public void postComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .build();
        commentService.postComment(comment, commentDTO.getBoardId());
    }

    @PutMapping("/putComment")
    public void putComment(@RequestBody CommentDTO commentDTO) {
        commentService.putComment(commentDTO);
    }


    @Transactional
    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable(name = "commentId") Long commentId) { commentService.deleteComment(commentId);}
}

