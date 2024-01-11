package com.sparta.wangnyang.domain.comment.service

import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest
import com.sparta.wangnyang.domain.comment.dto.UpdateCommentRequest
import com.sparta.wangnyang.entity.Comment
import org.springframework.data.domain.Page

interface CommentService {
//    fun getComment(): List<CommentResponse>

    fun getComment(): Page<Comment>

    fun createComment(boardId: Long, createCommentRequest: CreateCommentRequest): CommentResponse

    fun updateComment(userId:String, boardId: Long, updateCommentRequest: UpdateCommentRequest): CommentResponse

    fun deleteComment(userId:String, commentId: Long)

    fun getSubComments(commentId: Long): List<CommentResponse>

    fun createSubComment(boardId: Long, parentId: Long, createCommentRequest: CreateCommentRequest): CommentResponse

    fun updateSubComment(userId: String, commentId: Long, updateCommentRequest: UpdateCommentRequest): CommentResponse

    fun deleteSubComment(userId: String, commentId: Long)
}