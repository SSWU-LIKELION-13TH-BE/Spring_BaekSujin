package org.example.session3.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.session3.entitiy.Board;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String writer;

    private LocalDate commentDate;

    @PrePersist
    protected void onCreate() {
        this.commentDate = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    @JsonBackReference
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCommentDate(LocalDate date) {
        this.commentDate = date;
    }

}
