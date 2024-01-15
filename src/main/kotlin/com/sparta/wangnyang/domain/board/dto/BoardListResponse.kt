package com.sparta.wangnyang.domain.board.dto

import com.sparta.wangnyang.entity.Board

class BoardListResponse(board: Board) {
    val id: Long = board.id!!;
    val title: String = board.title
    val mainText: String = board.mainText
    val writer: String= board.userId
    val createdAt: String = board.createdAt.toString()

}


      
