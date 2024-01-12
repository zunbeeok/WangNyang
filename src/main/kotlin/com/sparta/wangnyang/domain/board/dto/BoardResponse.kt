package com.sparta.wangnyang.domain.board.dto

import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.entity.Comment

data class BoardResponse(
    val id : Long,
    val title : String,
    val mainText : String,
    val writer : String,
    val createdAt : String,
    val commentList : List<CommentResponse>,
)
