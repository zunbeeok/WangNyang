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
) {
    companion object {
        fun fromEntity(entity: Comment) = CommentResponse(
            id = entity.id,
            writer = entity.userId,
            text = entity.text,
            createdAt = entity.createdAt,
            boardId = entity.board,
        )
    } // 관심사의 분리. DTO랑 Entity는 서로 몰라야 하니까 변환로직은 DTO에 넣기.
}