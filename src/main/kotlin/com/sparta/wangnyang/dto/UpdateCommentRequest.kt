package com.sparta.wangnyang.dto

import java.util.Date

data class UpdateCommentRequest(
    val writer: String,
    val text: String
    val updated_at: Date
)