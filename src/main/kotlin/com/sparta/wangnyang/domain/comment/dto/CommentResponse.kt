package com.sparta.wangnyang.domain.comment.dto

import com.sparta.wangnyang.entity.Board
import com.sparta.wangnyang.entity.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long?,
    val writer: String,
    val text: String,
    val createdAt: LocalDateTime,
    var boardId: Board,
    val parentId: Long?,
    val subComments : MutableList<Comment>,
) {
    companion object {
        fun fromEntity(entity: Comment) = CommentResponse(
            id = entity.id,
            writer = entity.userId,
            text = entity.text,
            createdAt = entity.createdAt,
            boardId = entity.board,
            parentId = entity.parent?.id,
            subComments = entity.subComment
        )
    }
}