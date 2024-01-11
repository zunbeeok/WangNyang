package com.sparta.wangnyang.domain.user.service

import com.sparta.wangnyang.common.authority.TokenInfo
import com.sparta.wangnyang.domain.user.dto.*
import jakarta.servlet.http.HttpServletResponse

interface UserService {

    fun logIn(loginRequest: LoginRequest): LoginResponse
//    fun logIn(loginId: String, pw: String, response: HttpServletResponse): UserResponse

    fun signUp(request: SignUpRequest): UserResponse

    fun getUserInfo(loginId: String): UserResponse

    fun updateUserInfo(loginId: String, request: UpdateUserRequest): UserResponse
//
    fun deleteUserInfo(loginId: String)
}