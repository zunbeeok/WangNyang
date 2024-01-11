package com.sparta.wangnyang.domain.user.service

import com.sparta.wangnyang.common.authority.TokenInfo
import com.sparta.wangnyang.domain.user.dto.LoginRequest
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse

interface UserService {

    fun logIn(loginRequest: LoginRequest): TokenInfo
//    fun logIn(loginId: String, pw: String, response: HttpServletResponse): UserResponse

    fun signUp(request: SignUpRequest): UserResponse

    fun getUserInfo(loginId: String): UserResponse

//    fun updateUserInfo(loginId: String, request: UpdateUserRequest): UserResponse
//
//    fun deleteUserInfo(loginId: String)
}