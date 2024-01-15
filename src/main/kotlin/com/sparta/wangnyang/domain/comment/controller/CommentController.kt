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

//    @GetMapping
//    fun getComments(@PathVariable boardId: Long): ResponseEntity<List<CommentResponse>> {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(commentService.getComments(boardId))
//    }
//
    @GetMapping("/{commentId}")
    fun getComment(@PathVariable boardId: Long, @PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getComment(commentId))
    }

    @PostMapping
    fun createComment(@AuthenticationPrincipal user: User,@PathVariable boardId: Long, @RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.createComment(user.username, boardId, createCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(@AuthenticationPrincipal user: User, @PathVariable commentId: Long, @RequestBody updateCommentRequest: UpdateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.updateComment(user.username, commentId, updateCommentRequest))
    }


    @DeleteMapping("/{commentId}")
    fun deleteComment(@AuthenticationPrincipal user: User, @PathVariable boardId: Long, @PathVariable commentId: Long): ResponseEntity<Unit> {
        commentService.deleteComment(user.username, boardId, commentId)
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }


    @GetMapping("/{parentId}/subcomment")
    fun getSubComments(@PathVariable boardId: Long, @PathVariable parentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK).body(commentService.getSubComments(parentId))
    }

    @GetMapping("/{parentId}/subcomment/{subCommentId}")
    fun getSubComment(@PathVariable boardId: Long, @PathVariable parentId: Long, @PathVariable subCommentId: Long): ResponseEntity<SubCommentResponse>  {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getSubComment(parentId, subCommentId))
    }

    @PostMapping("/{parentId}/subcomment")
    fun createSubComment(
            @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable parentId: Long,
        @RequestBody createSubCommentRequest: CreateSubCommentRequest
    ): ResponseEntity<SubCommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createSubComment(user.username, parentId, createSubCommentRequest))
    }

    @PutMapping("/{parentId}/subcomment/{subCommentId}")
    fun updateSubComment(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable parentId: Long,
        @PathVariable subCommentId: Long,
        @RequestBody updateSubCommentRequest: UpdateSubCommentRequest,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateSubComment(user.username,parentId, subCommentId, updateSubCommentRequest))
    }


    @DeleteMapping("/{parentId}/subcomment/{subCommentId}")
    fun deleteSubComment(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable parentId: Long,
        @PathVariable subCommentId: Long,
    ): ResponseEntity<Unit> {
        commentService.deleteSubComment(user.username, parentId, subCommentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}