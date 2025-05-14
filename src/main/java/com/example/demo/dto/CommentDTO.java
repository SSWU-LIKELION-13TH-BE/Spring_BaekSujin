package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CommentDTO {
    private Long commentId;
    private String content;
    private String writer;
    private Date postDate;
    private Long boardId;
}