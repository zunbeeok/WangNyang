package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.SubCommentResponse
import jakarta.persistence.*

@Entity
@Table(name = "subcomment")
class SubComment(
    @Column(name = "user_id")
    var userId: String,

    @Column(name = "text")
    var text: String,


//    @Column(name = "parent_id")
//    var parentId: Long,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val comment: Comment

): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? =null;
    }


fun SubComment.toResponse(): SubCommentResponse {
    return SubCommentResponse(
            id=id!!,
        userId = userId,
        text = text
    )
}