package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UpdateUserRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse
import jakarta.persistence.*
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.password.PasswordEncoder


@Entity
@Table(name = "user", schema = "public")
class User(


    @Column(name = "login_id", length = 30)
    var loginId: String,

    @Column(name = "pw", length = 100)
    var pw: String,

    @Column(name = "name", length = 10)
    var name: String,

    @Column(name = "hp", length = 20)
    var hp: String,

    ) : BaseTimeEntity() {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null


    }

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        loginId = loginId,
        name = name,
        hp = hp,
    )
}
