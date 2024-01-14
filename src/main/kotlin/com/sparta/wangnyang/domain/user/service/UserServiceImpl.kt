package com.sparta.wangnyang.domain.user.service
import com.sparta.wangnyang.common.authority.JwtTokenProvider
import com.sparta.wangnyang.common.authority.TokenInfo
import com.sparta.wangnyang.domain.user.dto.*
import com.sparta.wangnyang.domain.user.repository.UserRepository
import com.sparta.wangnyang.entity.User
import com.sparta.wangnyang.entity.toResponse

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.math.log

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider:JwtTokenProvider,
    private val encoder: PasswordEncoder
):UserService {

    @Transactional
    override fun logIn(loginRequest : LoginRequest): LoginResponse {
            val user = userRepository.findByLoginId(loginRequest.loginId)
                    ?.takeIf { encoder.matches(loginRequest.pw,it.pw) }
                    ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
            val token = jwtTokenProvider.createToken("${user.loginId}:${MemberType.MEMBER}");
            return LoginResponse(user.loginId,user.name,token);
    }


    // 회원 가입
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        val checkUser = userRepository.existsUserByLoginId(request.loginId)

        // 비어있지 않고 아이디가 조회되면 생성 불가
        if (checkUser) {
            throw Exception("이미 존재하는 아이디입니다.")
        }

        return userRepository.save(
                User(
                        loginId = request.loginId,
                        name = request.name,
                        pw = encoder.encode(request.pw),
                        hp = request.hp,
                )
        ).toResponse()
    }


    // 회원 조회
    // 찾고자 하는 아이디가 존재하지 않으면 조회 불가
    override fun getUserInfo(loginId: String): UserResponse {
        val user: User = userRepository.findByLoginId(loginId) ?: throw Exception("존재하지 않는 아이디입니다.")
        return user.toResponse()
    }


    // 회원 정보 수정
    // 입력한 로그인 아이디가 DB에 없으면 수정 불가
    @Transactional
    override fun updateUserInfo(loginId: String, request: UpdateUserRequest): UserResponse {
        val user: User = userRepository.findByLoginId(loginId) ?: throw Exception("존재하지 않는 아이디입니다.")

        if(loginId != user.loginId) throw Exception("회원 정보가 일치하지 않습니다.")

        user.name = request.name
        user.pw = encoder.encode(request.pw)
        user.hp = request.hp

        userRepository.save(user)

        return user.toResponse();
    }

    // 회원 탈퇴
    // 입력한 로그인 아이디가 DB에 없으면 삭제 불가
    @Transactional
    override fun deleteUserInfo(loginId: String) {
        val user: User = userRepository.findByLoginId(loginId) ?: throw Exception("존재하지 않는 아이디입니다.")
        if(loginId != user.loginId) throw Exception("회원 정보가 일치하지 않습니다.")
        userRepository.delete(user)
    }
}






