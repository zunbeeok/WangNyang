package com.sparta.wangnyang.domain.user.repository

import com.sparta.wangnyang.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByLoginId(loginId: String): User?

}