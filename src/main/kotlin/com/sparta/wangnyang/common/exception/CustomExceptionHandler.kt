package com.sparta.wangnyang.common.exception

import com.sparta.wangnyang.common.dto.BaseResponse
import com.sparta.wangnyang.common.status.ResultCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionHandler {

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

    @ExceptionHandler(SignatureException::class)
    fun handleSignatureException() =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse(ResultCode.ERROR.name,"토큰이 유효하지 않습니다.",ResultCode.ERROR.message))

    @ExceptionHandler(MalformedJwtException::class)
    fun handleMalformedJwtException() =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse(ResultCode.ERROR.name,"올바르지 않은 토큰입니다.",ResultCode.ERROR.message))

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException() =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse(ResultCode.ERROR.name,"토큰이 만료되었습니다. 다시 로그인해주세요.",ResultCode.ERROR.message))

//    @ExceptionHandler(SignatureException::class)
//    fun handleLoginException() =
//            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse(ResultCode.ERROR.name,"로그인이 필요합니다.", ResultCode.ERROR.message))

}