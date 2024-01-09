package com.sparta.wangnyang.domain.user.service
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UpdateUserRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse
import com.sparta.wangnyang.domain.user.repository.UserRepository
import com.sparta.wangnyang.entity.User
import com.sparta.wangnyang.entity.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun logIn(loginId: String, pw: String) {
        TODO("Not yet implemented")
    }


    // 회원 가입
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        val user: User? = userRepository.findByLoginId(request.loginId)

        // 비어있지 않고 아이디가 조회되면 생성 불가
        if (user != null) {
            throw Exception("이미 존재하는 아이디입니다.")
        }

        return userRepository.save(
            User (
                logInId = request.loginId,
                name = request.name,
                pw = request.pw,
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


//    // 회원 정보 수정
//    // 입력한 로그인 아이디가 DB에 없으면 수정 불가
//    @Transactional
//    override fun updateUserInfo(loginId: String, request: UpdateUserRequest): UserResponse {
//        val user: User = userRepository.findByLoginId(loginId) ?: throw Exception("존재하지 않는 아이디입니다.")
//
//
//        if (request.loginId != user.logInId) {
//            throw Exception("존재하지 않는 아이디입니다.")
//        }
//
//        return userRepository.save(
//            User(
//                logInId = request.loginId,
//                name = request.name,
//                pw = request.pw,
//                hp = request.hp,
//            )
//        ).toResponse()
//        }
//
//
//    // 회원 탈퇴
//    // 입력한 로그인 아이디가 DB에 없으면 삭제 불가
//    @Transactional
//    override fun deleteUserInfo(loginId: String) {
//        val user: User = userRepository.findByLoginId(loginId) ?: throw Exception("존재하지 않는 아이디입니다.")
//        userRepository.delete(user)
//    }
}


