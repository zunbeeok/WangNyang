package com.sparta.wangnyang.domain.board.service

import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.domain.board.dto.CreateBoardRequest
import com.sparta.wangnyang.domain.board.dto.UpdateBoardRequest

interface BoardService {

    fun getAllBoardList(): List<BoardResponse>

    fun getBoardById(boardId: Long): BoardResponse

    fun createBoard(request: CreateBoardRequest): BoardResponse

    fun updateBoard(boardId: Long, request: UpdateBoardRequest): BoardResponse

    fun deleteBoard(boardId: Long)

}