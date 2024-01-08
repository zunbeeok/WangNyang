package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class Board(
        var title : String,
        var mainText:String,
        var userId:String,
):BaseTimeEntity(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? =null;
}
