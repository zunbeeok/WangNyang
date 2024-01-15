package com.sparta.wangnyang.domain.board.repository

import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface BoardRepository : JpaRepository<Board, Long> {

    // pageable 은 페이지 처리에 필요한 정보를 전달하는 용도의 타입,
    // of()함수를 이용해서 페이지 번호, 페이지 크기, 정렬방향 및 정렬 속성을 인자로 받아 PageRequest 객체가 생성됨,
    //
    fun findAllByOrderByCreatedAtDesc (pageable: Pageable): Page<Board>
}