package com.sparta.wangnyang.domain.file.repository

import com.sparta.wangnyang.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository: JpaRepository<Board, Long>