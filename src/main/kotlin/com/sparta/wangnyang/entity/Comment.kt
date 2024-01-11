package com.sparta.wangnyang.entity

import com.sparta.wangnyang.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment" , schema = "public")
class Comment(
    @Column
    var text: String,

    @Column
    var userId: String,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board,

    ):BaseTimeEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? =null;
}

