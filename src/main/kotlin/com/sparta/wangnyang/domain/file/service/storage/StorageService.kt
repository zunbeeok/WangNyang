package com.sparta.wangnyang.domain.file.service.storage

import com.sparta.wangnyang.domain.file.dto.FileResponse
import org.springframework.boot.CommandLineRunner
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream


interface StorageService {
//    // 초기화 작업
//    fun init(): CommandLineRunner
//
//    // 파일 저장
//    fun store(file: MultipartFile, extension: String): String

//    // 전체 파일 리스트 제공
//    fun loadAll(): Stream<Path>
//
//    // 단일 파일 경로 제공
//    fun load(fileName: String): Path
//
//    // 단일 파일 바이너리 제공
//    fun loadAsResource(fileName: String): Resource

//    // 파일 모두 삭제
//    fun deleteAll()

    fun uploadFile(userId: Long, file: MultipartFile): FileResponse?
}