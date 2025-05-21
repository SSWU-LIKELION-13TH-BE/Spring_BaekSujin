package org.example.session3.repository;

import org.example.session3.entitiy.Board;
import org.example.session3.entitiy.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    Optional<Comment> findByCommentId(Long commentId);

    void deleteByCommentId(Long commentId);
}
