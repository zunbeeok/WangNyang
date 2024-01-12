package com.sparta.wangnyang.domain.comment.dto

import java.time.LocalDateTime

data class UpdateCommentRequest(
    val id: Long,
    val boardId: Long,
    val writer: String,
    val text: String,
    val updatedAt: LocalDateTime,
)