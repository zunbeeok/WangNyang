package com.sparta.wangnyang.domain.board.service

import com.sparta.wangnyang.domain.board.dto.BoardListResponse
import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.domain.board.dto.CreateBoardRequest
import com.sparta.wangnyang.domain.board.dto.UpdateBoardRequest
import org.springframework.data.domain.Page
import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest

interface BoardService {
  
    fun getAllBoardList(): Page<BoardListResponse>

    fun getBoardById(boardId: Long): BoardResponse

    fun createBoard(userId:String,request: CreateBoardRequest): BoardResponse

    fun updateBoard(userId: String, boardId: Long, request: UpdateBoardRequest): BoardResponse

    fun deleteBoard(userId: String, boardId: Long)


}