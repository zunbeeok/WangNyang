package com.sparta.wangnyang.common.exception.dto

import org.aspectj.bridge.Message

// 파일 업로드 관련 에러에 쓰임
data class ErrorResponse (
    val message: String?
)