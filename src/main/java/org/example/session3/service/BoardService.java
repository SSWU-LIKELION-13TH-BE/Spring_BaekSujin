package org.example.session3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.session3.dto.BoardDTO;
import org.example.session3.entitiy.Board;
import org.example.session3.repository.BoardRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final이 붙은 필드만 생성자 생성
@Slf4j // log 찍을 수 있게 해주는 어노테이션

// 왜 print가 아니고 log ????
// 로그 레벨(Level) 제공 INFO, DEBUG, WARN, ERROR
// 로그 파일(.log)로 저장해서 운영 환경에서도 확인 가능 등 여러가지 사유로 print 대신 log로 출력 찍어본다.


public class BoardService {
    private final BoardRepository boardRepository;
    private final org.example.session3.service.S3Service s3Service;


    // 들어온 boardId 값과 db의 boardId 값이 일치하는 row 가져오기
    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    // 게시글 하나 작성
    public void postBoard(Board board) {
        boardRepository.save(board);
    }

    // 게시글 수정
    // 메서드 내에서 데이터베이스 작업이 실패하면 롤백됨.

//    public void putBoard(BoardDTO boardDTO) {
//                Board board = Board.builder()
//                        .boardId(boardDTO.getBoardId())
//                        .title(boardDTO.getTitle())
//                        .content(boardDTO.getContent())
//                        .writer(boardDTO.getWriter())
//                        .postDate(LocalDate.now())
//                        .build();

                // save 시 기존에 있는 객체라면 merge
                // id를 전달하지 않을 경우 Insert 수행
                // id를 전달할 경우 해당 id에 대한 데이터가 있다면 Update
                // id를 전달할 경우 해당 id에 대한 데이터가 없다면 Insert
//                boardRepository.save(board);
    @Transactional
    public void putBoard(BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardDTO.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());
        board.setPostDate(LocalDate.now());
    }


    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteByBoardId(boardId);
    }

    @org.springframework.transaction.annotation.Transactional
    //이미지 포함 게시글 생성
    public void ImageBoard(BoardDTO request) throws IOException {

        String savedImageURI = s3Service.upload(request.getImage()); //이미지 s3에 업로드하고 url 가져오기

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .image(savedImageURI) //img url 넣기
                .build();

        boardRepository.save(board);

    }

    // ID로 게시글 조회
    public BoardDTO getUpload(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isEmpty()) {
            return null;
        }
        Board board = optionalBoard.get();

        return BoardDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .imageUrl(board.getImage())
                .build();
    }

}
