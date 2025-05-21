package org.example.session3.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 15, nullable = false)
    private Long boardId;

    @Column(length = 15, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String writer;

    // 날짜만 표시 시간 X
    private LocalDate postDate;

    @PrePersist // jpa의 콜백 메서드 엔티티가 처음 저장되기 직전에 실행 즉 새로운 row 생성시 현재 날짜 저장
    protected void onCreate() {
        this.postDate = LocalDate.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonManagedReference  // 양방향 매핑 시, 무한 순환 문제를 피하게 해주는 어노테이션
    private List<Comment> comments = new ArrayList<>();
}
