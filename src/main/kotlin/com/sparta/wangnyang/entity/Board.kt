package com.sparta.wangnyang.entity

import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.common.BaseTimeEntity
import jakarta.persistence.CascadeType

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table



@Entity
@Table(name = "board")
data class Board(
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf(),

    @Column(name = "title")
    var title : String,

    @Column(name = "mainText")
    var mainText:String,

    @Column(name = "userId")
    var userId:String

):BaseTimeEntity(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? =null;

}

fun Board.toResponse(): BoardResponse {
    return BoardResponse(
        id = id!!,
        title = title,
        mainText = mainText,
        writer = userId,
        createdAt = createdAt.toString()
    )
}