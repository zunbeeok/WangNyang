package com.sparta.wangnyang.domain.user.dto


data class LoginResponse(
        val loginId :String,
        val name :String,
        val token:String
){
//    val type:MemberType = MemberType.MEMBER
}

enum class  MemberType{
    MEMBER
}
