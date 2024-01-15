package com.sparta.wangnyang.domain.comment.service

import com.sparta.wangnyang.domain.comment.dto.*

interface CommentService {
//    fun getComment(): List<CommentResponse>

//    fun createComment(boardId: Long, createCommentRequest: CreateCommentRequest): CommentResponse

//    fun updateComment(userId:String, boardId: Long, updateCommentRequest: UpdateCommentRequest): CommentResponse

//    fun deleteComment(userId:String, commentId: Long, parentId: Long)

    fun getSubComment(parentId: Long): CommentResponse

    fun createSubComment(parentId: Long, createSubCommentRequest: CreateSubCommentRequest): SubCommentResponse

    fun updateSubComment(parentId: Long, subCommentId: Long, updateSubCommentRequest: UpdateSubCommentRequest): CommentResponse

    fun deleteSubComment(userId:String, parentId: Long, subCommentId: Long)
}