package com.sparta.board.dto;

import com.sparta.board.entity.Board;
import lombok.Getter;

import java.util.Date;

@Getter
public class BoardResponseDto {

    // 아이디
    private Long id;
    //제목
    private String title;
    //작성자명
    private String username;
    //내용
    private String contents;
    //작성일
    private Date nowDate;

    //Board 객체를 입력받아서 BoardResponseDto객체를 생성.
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.nowDate = board.getNowDate();
    }
}
