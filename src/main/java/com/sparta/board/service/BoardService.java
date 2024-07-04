package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시글 작성
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        //RequestDto -> Entity
        Board board = new Board(requestDto);
        // DB 저장
        Board saveBoard = boardRepository.save(board);
        // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        return boardResponseDto;
    }

    //게시글 전체 조회
    public List<BoardResponseDto> getBoards() {
        // DB 조회
        List<BoardResponseDto> boardResponseDto = new ArrayList<>();
        List<Board> boards = boardRepository.findAllByOrderByNowDateDesc();

        for (Board board : boards) {
            boardResponseDto.add(new BoardResponseDto(board));
        }

        return boardResponseDto;
    }

    //게시글 선택 조회
    public BoardResponseDto getBoard(Long id) {
        //id가 없다면
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));
        //id가 있다면
        return new BoardResponseDto(board);
    }

    //게시글 수정
    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        //해당 게시글이 DB에 존재하는지 확인
        Board board = findBoard(id);

        // 비밀번호 일치 여부 확인
        if (board.getPassword().equals(requestDto.getPassword())) {
            board.update(requestDto);
            return id;
        } else {
            return 1L;
        }
    }

    //게시글 삭제
    public Long deleteBoard(Long id, BoardRequestDto requestDto) {
        //해당 메모가 DB에 존재하는 지 확인
        Board board = findBoard(id);

        // 비밀번호 일치 여부 확인
        if (board.getPassword().equals(requestDto.getPassword())) {
            boardRepository.delete(board);
            return id;
        } else {
            return 1L;
        }
    }

    private Board findBoard(Long id) {
        //id에 해당하는 게시글이 없으면
        return boardRepository.findById(id).orElseThrow(() ->
                //IllegalArgumentException 실행.
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
