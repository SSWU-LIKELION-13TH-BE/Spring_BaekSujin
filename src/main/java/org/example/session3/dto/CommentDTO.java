package org.example.session3.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long commentId;
    private String content;
    private String writer;
    private Date commentDate;

    private Long boardId;  // 어떤 게시글에 달린 댓글인지 나타내기 위함
}
