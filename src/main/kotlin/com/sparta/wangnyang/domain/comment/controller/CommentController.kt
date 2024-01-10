package com.sparta.wangnyang.domain.comment.controller

import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest
import com.sparta.wangnyang.domain.comment.dto.UpdateCommentRequest
import com.sparta.wangnyang.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @GetMapping
    fun getComment(): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK).body(commentService.getComment())
    }

    @PostMapping
    fun createComment(@PathVariable boardId: Long, @RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(boardId, createCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Unit> {
        commentService.deleteComment(commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}