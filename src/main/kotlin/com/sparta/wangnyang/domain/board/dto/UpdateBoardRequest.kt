package com.sparta.wangnyang.domain.board.dto

import javax.xml.crypto.Data

data class UpdateBoardRequest(
    val title : String,
    val mainText : String,
//    val image: Data
)
