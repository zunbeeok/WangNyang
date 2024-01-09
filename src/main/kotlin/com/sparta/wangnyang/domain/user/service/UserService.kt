package com.sparta.wangnyang.domain.user.service

import com.sparta.wangnyang.domain.user.dto.LoginForm
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UpdateUserRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse

interface UserService {

    fun logIn(loginId: String, pw: String)

    fun signUp(request: SignUpRequest): UserResponse

    fun getUserInfo(loginId: String): UserResponse

//    fun updateUserInfo(loginId: String, request: UpdateUserRequest): UserResponse
//
//    fun deleteUserInfo(loginId: String)
}