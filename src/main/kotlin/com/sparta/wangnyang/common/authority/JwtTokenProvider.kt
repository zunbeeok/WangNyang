package com.sparta.wangnyang.common.authority

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.IllegalFormatException
import javax.crypto.spec.SecretKeySpec


const val EXPIRATION_MILLISECONDS: Long = 1000 * 60 * 30

@Component
class JwtTokenProvider (
        @Value("\${secret-key}")
        private var secretKey:String,

        @Value("\${expiration-hours}")
        private val expirationHours: Long,

        @Value("\${issuer}")
        private val issuer: String
){

    /**
     * 토큰 생성
     */
    fun createToken(userSpecification: String) = Jwts.builder()
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS512.jcaName)) // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
            .setSubject(userSpecification)   // JWT 토큰 제목
            .setIssuer(issuer)    // JWT 토큰 발급자
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
            .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰의 만료시간 설정
            .compact()!!    // JWT 토큰 생성


    /**
     * 토큰 검증
     */
    fun validateTokenAndGetSubject(token: String): String? = Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
            .body
            .subject


}