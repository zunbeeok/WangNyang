package com.sparta.wangnyang.common.dto

import com.sparta.wangnyang.common.status.ResultCode

data class BaseResponse<T>(
        val resultCode: String = ResultCode.SUCCESS.name,
        val data:T? =null,
        val message:String = ResultCode.SUCCESS.message
)
