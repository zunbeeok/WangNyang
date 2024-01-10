package com.sparta.wangnyang.domain.comment.dto

import java.time.LocalDateTime

data class CreateCommentRequest(
    val writer: String,
    val text: String,
)