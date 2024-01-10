package com.sparta.wangnyang.domain.comment.service

import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest
import com.sparta.wangnyang.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun getComment(): List<CommentResponse>
    fun createComment(boardId: Long, createCommentRequest: CreateCommentRequest): CommentResponse
    fun updateComment(boardId: Long, updateCommentRequest: UpdateCommentRequest): CommentResponse
    fun deleteComment(commentId: Long)
}