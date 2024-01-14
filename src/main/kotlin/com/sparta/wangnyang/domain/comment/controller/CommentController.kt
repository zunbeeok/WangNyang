package com.sparta.wangnyang.domain.comment.controller

import com.sparta.wangnyang.domain.comment.dto.*
import com.sparta.wangnyang.domain.comment.service.CommentService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/board/{boardId}/comment")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping("/{parentId}/subComment")
    fun getSubComments(@PathVariable boardId: Long, @PathVariable parentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK).body(commentService.getSubComment(parentId))
    }

    @PostMapping("/{parentId}/subComment")
    fun createSubComment(
        @PathVariable boardId: Long,
        @PathVariable parentId: Long,
        @RequestBody createSubCommentRequest: CreateSubCommentRequest
    ): ResponseEntity<SubCommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createSubComment(parentId, createSubCommentRequest))
    }

    @PutMapping("/{parentId}/subComment/{subCommentId}")
    fun updateSubComment(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable parentId: Long,
        @PathVariable subCommentId: Long,
        @RequestBody updateSubCommentRequest: UpdateSubCommentRequest,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateSubComment(parentId, subCommentId, updateSubCommentRequest))
    }


    @DeleteMapping("/{parentId}/subComment/{subCommentId}")
    fun deleteSubComment(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable parentId: Long,
        @PathVariable subCommentId: Long,
    ): ResponseEntity<Unit> {
        commentService.deleteSubComment(parentId, subCommentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}