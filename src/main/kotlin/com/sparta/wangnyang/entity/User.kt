package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UpdateUserRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder


@Entity
@Table(name = "user", schema = "public")
data class User(



    @Column(name = "login_id", length = 30)
    val loginId: String,

    @Column(name = "pw", length = 100)
    val pw: String,

    @Column(name = "name", length = 10)
    val name: String,

    @Column(name = "hp", length = 20)
    val hp: String,

    ) : BaseTimeEntity() {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null

//        companion object{
//            fun from(request:SignUpRequest, encoder: PasswordEncoder)=User(
//                    name = request.name,
//                    hp = request.hp,
//                    pw = encoder.encode(request.pw),
//                    loginId = request.loginId
//            )
//        }

//        fun update(updateUserRequest: UpdateUserRequest, encoder: PasswordEncoder){
//            this.pw = updateUserRequest.pw
//                    .takeIf { it.isNotBlank() }
//                    ?.let { encoder.encode(it) }
//                    ?: this.pw
//            this.name = updateUserRequest.name
//            this.hp = updateUserRequest.hp
//
//
//        }
    }

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        loginId = loginId,
        name = name,
        hp = hp,
    )



}
