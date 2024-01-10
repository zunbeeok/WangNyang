package com.sparta.wangnyang.domain.comment.dto

import java.time.LocalDateTime

data class CreateCommentRequest(
    val id: Long,
    val writer: String,
    val text: String,
    val createdAt: LocalDateTime,
    val boardId: Long,
)