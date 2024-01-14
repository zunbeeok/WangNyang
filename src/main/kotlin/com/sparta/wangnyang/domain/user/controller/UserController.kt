package com.sparta.wangnyang.domain.user.controller

import com.sparta.wangnyang.common.authority.TokenInfo
import com.sparta.wangnyang.domain.user.dto.*
import com.sparta.wangnyang.domain.user.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*


@RequestMapping("/user")
@RestController

class UserController(
    private val userService: UserService
) {


    // 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(signUpRequest))
    }



    // 세션을 받아서
    // 로그인
    @PostMapping("/login")
    fun logIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status((HttpStatus.OK))
            .body(userService.logIn(loginRequest))
    }




    // 회원 정보 조회
    @GetMapping("/{loginId}")
    fun getUserInfo(@PathVariable loginId: String): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUserInfo(loginId))
    }


    // 회원 정보 수정
    @PutMapping()
    fun updateUserInfo(
            @AuthenticationPrincipal user:User,//{username :loginId, paswword :"", :MEMBER}
        @RequestBody updateUserInfo: UpdateUserRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUserInfo(user.username, updateUserInfo))
    }

    // 회원 탈퇴
    @DeleteMapping()
    fun deleteUserInfo(@AuthenticationPrincipal user:User): ResponseEntity<Unit> {
        userService.deleteUserInfo(user.username)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}
