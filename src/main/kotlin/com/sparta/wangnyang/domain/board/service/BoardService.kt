package com.sparta.wangnyang.domain.board.service

import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.domain.board.dto.CreateBoardRequest
import com.sparta.wangnyang.domain.board.dto.UpdateBoardRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest
import java.awt.print.Pageable

interface BoardService {
  
    fun getAllBoardList(): Page<BoardResponse>

    fun getBoardById(boardId: Long): BoardResponse

    fun createBoard(request: CreateBoardRequest): BoardResponse

    fun updateBoard(userId: String, boardId: Long, request: UpdateBoardRequest): BoardResponse

    fun deleteBoard(userId: String, boardId: Long)
    
    fun createComment(boardId: Long, request: CreateCommentRequest): CommentResponse

    fun deleteComment(parentId: Long)

}