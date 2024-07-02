package com.sparta.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    //제목
    private String title;

    //작성자명
    private String username;

    //비밀번호
    private String password;

    //작성 내용
    private String contents;
}

