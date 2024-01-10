package com.sparta.wangnyang.domain.board.dto

data class UpdateBoardRequest(
    val title : String,
    val mainText : String,
    val writer : String,
)
