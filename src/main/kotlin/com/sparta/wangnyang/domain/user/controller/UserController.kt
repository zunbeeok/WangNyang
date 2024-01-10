package com.sparta.wangnyang.domain.user.controller

import com.sparta.wangnyang.common.authority.TokenInfo
import com.sparta.wangnyang.domain.user.dto.LoginRequest
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse
import com.sparta.wangnyang.domain.user.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun logIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<TokenInfo> {
        return ResponseEntity
            .status((HttpStatus.OK))
            .body(userService.logIn(loginRequest))
    }




    ////서버에서 쿠키 생성하기
    //로그인
//    @PostMapping("/login")
//    fun logIn(
//        @ModelAttribute loginForm: LoginForm,
//        bindingResult: BindingResult,
//        httpServletResponse: HttpServletResponse
//    ): ResponseEntity<Unit> {
//
//        // 로그인에 실패하면 에러 던지기
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity
//                .status(HttpStatus.NO_CONTENT)
//                .build()
//        }
//
//        val loginMember = userService.logIn(loginForm.loginId, loginForm.pw)
//        // 로그인 하려는데 일치하는 아이디랑 비밀번호가 없으면 다시 로그인 페이지로 이동
//        if (loginMember == null) {
//            bindingResult.reject("Login Fail", "아이디 또는 비밀번호가 맞지 않습니다.")
//            return "/login/loiginForm"
//        }
//
//        //쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다. (브라우저 종료시 모두 종료)
//        val idCookie = Cookie(loginForm.loginId, loginForm.pw)
//        httpServletResponse.addCookie(idCookie)
//
//        return "redirect:/"
//    }
//
//
//    //// 서버에서 쿠키 조회하기
//    @GetMapping("/")
//    fun homeLogin(@CookieValue loginId: String, pw: String ): String{
////        if (loginId != null) {
////
////        }
////
//        TODO()
//    }






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