package org.example.session3.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BoardDTO {
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private LocalDate postDate;

    private MultipartFile image; // 업로드용
    private String imageUrl; // URL 경로, 조회용
}
