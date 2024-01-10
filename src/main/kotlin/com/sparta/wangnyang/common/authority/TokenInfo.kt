package com.sparta.wangnyang.common.authority

data class TokenInfo(
        val grantType:String, //jwt 권한의 인증 타입
        val accessToken:String //accessToken은 실제로 검증할 토큰
)
