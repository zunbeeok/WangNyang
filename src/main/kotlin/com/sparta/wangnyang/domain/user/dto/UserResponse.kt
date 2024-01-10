package com.sparta.wangnyang.domain.user.dto


data class UserResponse(
    val id: Long,
    val loginId: String,
    val name: String,
    val hp: String,
)

