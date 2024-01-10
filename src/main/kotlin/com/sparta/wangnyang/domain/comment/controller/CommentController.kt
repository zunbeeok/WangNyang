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

    // GET 이라는 HTTP 메서드 사용해서, 특정 게시물의 모든 댓글을 조회함
    // HTTP 상태코드 200(OK)랑 조회한 댓글 목록을 응답한다.

    @PostMapping
    fun createComment(@PathVariable boardId: Long, @RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(boardId, createCommentRequest))
    }
    // POST를 사용해서 특정 게시물에 새로운 댓글을 생성함.
    // CreateCommentRequest를 통해서 전달된 정보로 댓글 생성함.
    // 상태코드 201랑 생성된 댓글 정보 응답함.

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