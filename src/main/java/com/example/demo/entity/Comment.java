package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commentId", unique=true, nullable=false)
    private Long commentId;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String writer;

    private LocalDate postDate;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @PrePersist
    protected void onCreate() {
        this.postDate = LocalDate.now();
    }
}
