package com.sparta.wangnyang.common.authority

import com.sparta.wangnyang.domain.user.dto.MemberType
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
        private val jwtTokenProvider: JwtTokenProvider
):OncePerRequestFilter(){ //genericFilterBean

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val token = parseBearerToken(request)
            val user = parseUserSpecification(token)
            UsernamePasswordAuthenticationToken.authenticated(user, token, user.authorities)
                    .apply { details = WebAuthenticationDetails(request) }
                    .also { SecurityContextHolder.getContext().authentication = it }
        }catch (e:Exception){
            request.setAttribute("exception",e)
        }

        filterChain.doFilter(request,response)
    }

    private fun parseBearerToken(request: HttpServletRequest) = request.getHeader(HttpHeaders.AUTHORIZATION)
            .takeIf { it?.startsWith("Bearer ", true) ?: false }?.substring(7)

    private fun parseUserSpecification(token: String?):User {
            return (token?.takeIf { it.length >= 10 }
                    ?.let { jwtTokenProvider.validateTokenAndGetSubject(it) }
                    ?:"anonymous:anonymous"
//                    ?: throw Exception("에러 확인.")//상위 클래스로 넘겨주고 거기서 에러컨트롤 /인증, 권한을 체크 authentication 타입에러로 던져 주기.
            // loginId:"MEMBER"
            ).split(":")
            //["logindId","MEMBER]
            .let { User(it[0], "", listOf(SimpleGrantedAuthority(it[1]))) }
    }
}