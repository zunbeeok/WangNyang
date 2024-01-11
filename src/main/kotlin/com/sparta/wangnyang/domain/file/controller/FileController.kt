import com.sparta.wangnyang.domain.file.dto.FileResponse
import com.sparta.wangnyang.domain.file.service.storage.FileStorageServiceImpl
import com.sparta.wangnyang.domain.file.service.storage.StorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

//package com.sparta.wangnyang.domain.file.controller
//
//import com.sparta.wangnyang.domain.file.dto.FileResponse
//import com.sparta.wangnyang.domain.file.service.storage.StorageService
//import org.springframework.boot.CommandLineRunner
//import org.springframework.context.annotation.Bean
//import org.springframework.core.io.InputStreamResource
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import org.springframework.web.multipart.MultipartFile
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileNotFoundException
//import java.io.FileOutputStream
//import java.nio.file.Paths
//import java.util.*
//
//
//// MultipartFile 을 쓴 이유는 getOriginalFilename()로 파일명과 확장자까지 붙인 문자열 반환을 받아,
//// 완전한 경로를 File객체로 만들어서 MultipartFile의 transferTo 메서드를 호출하면 파일 저장이 완료된다.
////@RequestMapping("/board")
//@RequestMapping("/board")
//@RestController
//class FileController(
//    private val storageService: StorageService
//) {
//
//    @PostMapping("/uploadFile")
//    fun uploadFile(
//        @RequestParam("extension") extension: String,
//        @RequestParam("file") file: MultipartFile,
//    ): ResponseEntity<FileResponse> {
//        val fileName = UUID.randomUUID().toString()
//        val path = storageService.store(file, extension)
//        val fileResponse = FileResponse(fileName, path)
//        return ResponseEntity.ok()
//            .header(HttpHeaders.CONTENT_TYPE, "application/json")
//            .body(fileResponse)
//    }
//
//}
//
//
//


@RestController
@RequestMapping("/userFiles")
class FileController(
    private val storageService: StorageService,

) {
    @Value("\${file.upload - dir}")
    val uploadDir: String? = null

    @ResponseBody
    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("boardId") boardId: Long
    ): FileResponse? {

        try {
            return storageService.uploadFile(boardId, file)
        } catch (e: IOException) {
            return null
        }
    }
}