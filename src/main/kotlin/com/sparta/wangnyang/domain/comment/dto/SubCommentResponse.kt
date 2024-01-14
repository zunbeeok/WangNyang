package com.sparta.wangnyang.domain.comment.dto

data class SubCommentResponse(
    val id: Long,
    val parentId: Long,
//    val userId: String,
    val text: String,


) {


}