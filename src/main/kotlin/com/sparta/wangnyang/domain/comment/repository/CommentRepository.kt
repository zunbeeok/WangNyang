package com.sparta.wangnyang.domain.comment.repository

import com.sparta.wangnyang.entity.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByParent(parent: Comment): List<Comment>

    fun findAllByComment(pageable: Pageable): Page<Comment>


}