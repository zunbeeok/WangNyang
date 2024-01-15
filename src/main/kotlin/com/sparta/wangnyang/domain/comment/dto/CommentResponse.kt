package com.sparta.wangnyang.domain.comment.dto

import com.sparta.wangnyang.entity.Board
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val userId: String,
    val text: String,
    val createdAt: LocalDateTime,
//    var boardId: Board,
    val subCommentList: List<SubCommentResponse>,
)

