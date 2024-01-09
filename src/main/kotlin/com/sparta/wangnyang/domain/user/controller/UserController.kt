package com.sparta.wangnyang.domain.user.controller

import com.sparta.wangnyang.domain.user.dto.LoginForm
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UpdateUserRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse
import com.sparta.wangnyang.domain.user.service.UserService
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.Cookie
import org.springframework.boot.web.servlet.server.Session
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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


    ////서버에서 쿠키 생성하기
    //로그인
    @PostMapping("/login")
    fun logIn(
        @ModelAttribute loginForm: LoginForm,
        bindingResult: BindingResult,
        httpServletResponse: HttpServletResponse
    ): String {
        // 로그인에 실패하면 로그인 페이지로 다시 돌아가기
        if (bindingResult.hasErrors()) {
            return "/login/loiginForm"
        }

        val loginMember = userService.logIn(loginForm.loginId, loginForm.pw)
        // 로그인 하려는데 일치하는 아이디랑 비밀번호가 없으면 다시 로그인 페이지로 이동
        if (loginMember == null) {
            bindingResult.reject("Login Fail", "아이디 또는 비밀번호가 맞지 않습니다.")
            return "/login/loiginForm"
        }

        //쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다. (브라우저 종료시 모두 종료)
        val idCookie = Cookie(loginForm.loginId, loginForm.pw)
        httpServletResponse.addCookie(idCookie)

        return "redirect:/"
    }


    //// 서버에서 쿠키 조회하기
    @GetMapping("/")
    fun homeLogin(@CookieValue loginId: String, pw: String ): String{
//        if (loginId != null) {
//
//        }
//
        TODO()
    }






    // 회원 정보 조회
    @GetMapping("/{loginId}")
    fun getUserInfo(@PathVariable loginId: String): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUserInfo(loginId))
    }


//    // 회원 정보 수정
//    @PutMapping("/{loginId}")
//    fun updateUserInfo(
//        @PathVariable loginId: String,
//        @RequestBody updateUserInfo: UpdateUserRequest
//    ): ResponseEntity<UserResponse> {
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body(userService.updateUserInfo(loginId, updateUserInfo))
//    }
//
//
//    // 회원 탈퇴
//    @DeleteMapping("/{loginId}")
//    fun deleteUserInfo(@PathVariable loginId: String): ResponseEntity<Unit> {
//        return ResponseEntity
//            .status(HttpStatus.NO_CONTENT)
//            .build()
//    }
}
