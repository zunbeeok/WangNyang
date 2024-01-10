package com.sparta.wangnyang.domain.comment.dto


data class CreateCommentRequest(
    val writer: String,
    val text: String,
)