package org.example.session3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.session3.dto.CommentDTO;
import org.example.session3.entitiy.Board;
import org.example.session3.entitiy.Comment;
import org.example.session3.repository.BoardRepository;
import org.example.session3.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findByCommentId(commentId);
    }

    public void postComment(Comment comment, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));
        comment.setBoard(board);
        commentRepository.save(comment);
    }

    @Transactional
    public void putComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findByCommentId(commentDTO.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다."));

        comment.setContent(commentDTO.getContent());
        comment.setWriter(commentDTO.getWriter());
        comment.setCommentDate(LocalDate.now()); // 수정 시 날짜 변경 (선택)
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}

