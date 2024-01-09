package com.sparta.wangnyang.domain.user.dto


data class SignUpRequest(
    val loginId: String,
    val name: String,
    val pw: String,
    val hp: String,
)
