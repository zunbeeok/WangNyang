package com.sparta.wangnyang.controller

import com.sparta.wangnyang.dto.CommentResponse
import com.sparta.wangnyang.dto.CreateCommentRequest
import com.sparta.wangnyang.dto.UpdateCommentRequest
import com.sparta.wangnyang.entity.Comment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/board/{boardId}/comments")
@RestController
class CommentController {

    @GetMapping
    fun getComments(@PathVariable commentId: Long): ResponseEntity<List<CommentResponse>> {
        TODO("not implemented")
    }

    @PostMapping
    fun createComment(@RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse>{
        TODO("not implemented")
    }

    @PutMapping("/commentId")
    fun updateComment(@PathVariable commentId: Long, @RequestBody updateCommentRequest: UpdateCommentRequest): ResponseEntity<CommentResponse> {
        TODO("not implemented")
    }

    @DeleteMapping("/commentId")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Unit> {
        TODO("not implemented")
    }
}