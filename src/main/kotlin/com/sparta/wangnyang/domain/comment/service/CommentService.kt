package com.sparta.wangnyang.domain.comment.service

import com.sparta.wangnyang.domain.comment.dto.*

interface CommentService {

//    fun getComments(boardId: Long): List<CommentResponse>
//
    fun getComment(parentId: Long): CommentResponse

    fun createComment(userId:String, boardId: Long, request: CreateCommentRequest): CommentResponse

    fun updateComment(userId:String, parentId: Long, request: UpdateCommentRequest): CommentResponse

    fun deleteComment(userId:String, boardId: Long, parentId: Long)

    fun getSubComments(parentId: Long): CommentResponse

    fun getSubComment(parentId: Long, subCommentId: Long): SubCommentResponse

    fun createSubComment(userId:String, parentId: Long, request: CreateSubCommentRequest): SubCommentResponse

    fun updateSubComment(userId:String, parentId: Long, subCommentId: Long, request: UpdateSubCommentRequest): SubCommentResponse

    fun deleteSubComment(userId:String, parentId: Long, subCommentId: Long)
}