package com.sparta.board.repository;

import com.sparta.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository -> 첫 번째 제네릭 타입으로 Board: Repository가 다루는 엔티티 타입
//JpaRepository -> 두 번째 제네릭 타입으로 Long : 식별자의 타입
public interface BoardRepository extends JpaRepository<Board, Long> {
    //Board의 엔티티를 조회하는 메서드
    //nodDate 필드를 기준으로 내림차순 정렬.
    List<Board> findAllByOrderByNowDateDesc();
}
