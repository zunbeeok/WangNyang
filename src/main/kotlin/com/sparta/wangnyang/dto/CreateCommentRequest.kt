package com.sparta.wangnyang.dto

import java.util.Date

data class CreateCommentRequest(
    val writer: String,
    val text: String,
    val created_at: Date
)