package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findByCommentId(commentId);
    }

    public void postComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void putComment(CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .commentId(commentDTO.getCommentId())
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .postDate(LocalDate.now())
                .build();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
