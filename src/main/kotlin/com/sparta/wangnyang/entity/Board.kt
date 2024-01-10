package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import jakarta.persistence.*


@Entity
data class Board(
        var title : String,
        var mainText:String,
        var userId:String,

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()

):BaseTimeEntity(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? =null;


    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }
}