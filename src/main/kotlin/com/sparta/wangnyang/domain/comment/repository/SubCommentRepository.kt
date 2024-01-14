package com.sparta.wangnyang.domain.comment.repository

import com.sparta.wangnyang.entity.SubComment
import org.springframework.data.jpa.repository.JpaRepository

interface SubCommentRepository: JpaRepository<SubComment,Long> {
}