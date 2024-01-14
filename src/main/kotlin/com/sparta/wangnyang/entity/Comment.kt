package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(
    @Column(name = "text")
    var text: String,

    @Column(name = "user_id")
    var userId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board,

//
    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val subcomments: MutableList<SubComment> = mutableListOf(),

    ):BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null;

    fun createSubComment(subComment: SubComment) {
        subcomments.add(subComment)
    }

    fun removeSubComment(subComment: SubComment) {
        subcomments.remove(subComment)
    }

}
fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        userId = userId,
        text = text,
        createdAt = createdAt,
        boardId = board,
        subCommentList = subcomments.map { it.toResponse() }
    )

}
