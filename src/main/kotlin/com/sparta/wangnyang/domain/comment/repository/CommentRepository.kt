package com.sparta.wangnyang.domain.comment.repository

import com.sparta.wangnyang.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}