package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import com.sparta.wangnyang.domain.user.dto.UserResponse
import jakarta.persistence.*


@Entity
@Table(name = "user")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "loginId", length = 30)
    val loginId: String,

    @Column(name = "pw", length = 100)
    val pw: String,

    @Column(name = "name", length = 10)
    val name: String,

    @Column(name = "hp", length = 20)
    val hp: String,

    ) : BaseTimeEntity() {

    }

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        loginId = loginId,
        name = name,
        hp = hp,
    )



}
