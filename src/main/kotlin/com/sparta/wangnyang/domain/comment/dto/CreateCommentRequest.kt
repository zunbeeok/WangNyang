package com.sparta.wangnyang.domain.comment.dto

// 만들때부터 댓글 아이디 넘버와 함께 parentId를 같이 만들어서
data class CreateCommentRequest(
    val writer: String,
    val text: String,
)