package com.sparta.wangnyang.domain.board.repository

import com.sparta.wangnyang.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable

interface BoardRepository : JpaRepository<Board, Long>