package com.sparta.wangnyang.common.authority

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtAuthenticationFilter (
        private val jwtTokenProvider: JwtTokenProvider
):GenericFilterBean(){ //genericFilterBean

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val token = resolveToken(request as HttpServletRequest) // accessToken

        if(token != null && jwtTokenProvider.validateToken(token)){
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain?.doFilter(request,response)
    }

    private fun resolveToken(request:HttpServletRequest):String?{
        val bearerToken = request.getHeader("Authorization")

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            bearerToken.substring(7)
        }else{
            null;
        }
    }


}