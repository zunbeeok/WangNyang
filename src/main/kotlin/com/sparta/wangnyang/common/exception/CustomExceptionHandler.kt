package com.sparta.wangnyang.common.exception

import com.sparta.wangnyang.common.dto.BaseResponse
import com.sparta.wangnyang.common.status.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class CustomExceptionHandler {

    //에러가 발생시 BaseResponse에 맞춰 에러 객체를 생성해냄.
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNorValidException(ex:MethodArgumentNotValidException):ResponseEntity<BaseResponse<Map<String,String>>>{
        val errors = mutableMapOf<String,String>()
        ex.bindingResult.allErrors.forEach{ err ->
            val fieldName = (err as FieldError).field
            val errorMessage = err.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }

        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.message), HttpStatus.BAD_REQUEST)
    }
}