package org.example.session3.repository;

import org.example.session3.entitiy.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> { // jpaRepository 상속받아서 정의된 함수 사용
    // 게시글 가져오기 read (get)
    // Optional은 값이 있을 수도 있고, 없을 수도 있는 상황을 처리하는 컨테이너 객체
    Optional<Board> findByBoardId(Long boardId);

    // 게시글 작성하기 delete (delete)
    void deleteByBoardId(Long boardId);
}

// DB와의 직접적인 데이터 처리 (-> 아이디 가져오기?)