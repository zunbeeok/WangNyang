package com.sparta.wangnyang.domain.comment.dto

data class CreateSubCommentRequest(
    val parentId: Long,
    val writer: String,
    val text: String,

)
