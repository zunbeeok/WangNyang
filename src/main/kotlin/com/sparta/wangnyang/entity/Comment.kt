package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class Comment(
        var text:String

):BaseTimeEntity(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? =null;
}
