package com.sparta.wangnyang.domain.user.service
import com.sparta.wangnyang.domain.user.dto.SignUpRequest
import com.sparta.wangnyang.domain.user.dto.UserResponse
import com.sparta.wangnyang.domain.user.repository.UserRepository
import com.sparta.wangnyang.entity.User
import com.sparta.wangnyang.entity.toResponse
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
): UserService {


    // 쿠키에 로그인 아이디(name) 비밀번호(value) 넣음


    // 세션 아이디는 랜덤으로 받은 UUID
    // 랜덤 세션 Id를 담는 해쉬맵 저장소 - HashMap보다 더 안전한 ConcurrentHashMap 사용
    // 세션객체와 세션 아이디를 해쉬맵에 저장
    val sessionStore: MutableMap<String, Objects> = ConcurrentHashMap()



    // HttpServletResponse 객체에 Content Type, 응답코드, 응답 메시지등을 담아서 전송함.
    fun createSession(response: HttpServletResponse, cookieKey: String) {
        // uuid 랜덤 스트링 세션 생성
        val sessionId: String = UUID.randomUUID().toString()
        // 위에서 만들었던 가변 맵에 추가
        sessionStore[sessionId] = cookieKey

//        // 쿠키 생성 후 응답인 HttpServletResp response에 저장됨. 쿠키를 전달하는 HTTP 통신
//        val cookie = Cookie(pw, sessionId)
//        response.addCookie(cookie)

    }

    // 쿠키가 비어서 올수도 있고 채워져서 올수도 있음 => 응답 널값 허용
    fun getSession(cookieKey: String, request: HttpServletRequest): Any? {
        // findCookie(request,key)
        val cookie: Cookie = findCookie(request, cookieKey) ?: return null
        request.getSession()
        return sessionStore[cookie.value]
    }


    fun expire(pw: String, request: HttpServletRequest): Any? {
        val cookie: Cookie = findCookie(request, pw) ?: return null
        // 요청받은 쿠키가 비어있으면 널값 반환
        if (request.cookies == null) {
            return null
        }
        // 쿠키가 널값이 아니어야 해당 쿠키 삭제 가능
        return sessionStore.remove(cookie.value)

    }


    fun findCookie(request: HttpServletRequest, cookieKey: String): Cookie? {
        // 요청받은 쿠키가 비어있으면 널값 반환
        if (request.cookies == null) {
            return null
        }
        //  요청한 쿠키들을 배열로 만들어 순회하며 찾게함
        return Arrays.stream(request.cookies)
            // 쿠키 객체 c의 name(왕냥-사이트명)이 cookieKey와 일치하는지
            .filter { c -> c.name.equals(cookieKey) }
            .findAny()
            .orElse(null)
    }




    @Transactional
    override fun logIn(loginId: String, pw: String, response: HttpServletResponse): UserResponse {

//        // 유저의 비밀번호와 부여된 랜덤 세션 아이디가 답긴 해쉬맵
//        val response = createSession(pw, response)
        val cookie: Cookie = Cookie(sessionStore.sess)

        response.addCookie(cookie)
        createSession(pw, response)




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


