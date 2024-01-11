package com.sparta.wangnyang.domain.user.dto


data class UpdateUserRequest(
    val name: String,
    val pw: String,
    val hp: String,
)
