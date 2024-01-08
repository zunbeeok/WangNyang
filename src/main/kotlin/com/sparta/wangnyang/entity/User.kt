package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class User(
        var pw:String,
        var name:String,
        var hp:String,
):BaseTimeEntity(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :String?=null;
}
