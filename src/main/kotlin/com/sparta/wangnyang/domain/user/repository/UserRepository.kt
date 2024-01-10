package com.sparta.wangnyang.domain.user.repository

import com.sparta.wangnyang.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository: JpaRepository<User, Long> {
    fun findByLoginId(loginId: String): User?

    fun existsUserByLoginId(loginId: String):Boolean

}