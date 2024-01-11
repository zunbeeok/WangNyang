package com.sparta.wangnyang.domain.file.service.storage

import com.sparta.wangnyang.domain.board.repository.BoardRepository
import com.sparta.wangnyang.domain.file.dto.FileResponse
import com.sparta.wangnyang.domain.file.repository.FileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File


import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileStorageServiceImpl(
    private val boardRepository: BoardRepository
): StorageService {
    // 파일 경로
    @Value("\${file.upload-dir}")
    val uploadDir: String? = null

    // 이미지 파일은 최대 5MB
    val maxImageSize: Long = 5242880

    // 프로필 올리기
    override fun uploadFile(boardId: Long ,file: MultipartFile): FileResponse? {

        // 파일이 없거나 기본 파일이 아니면 파일 삭제 후 기본 파일로 저장
        if (file.isEmpty() || "".equals(file.name)) {
            val filePath = "/src/main/kotlin/com/sparta/wangnyang/domain/file/post"
            val uploadFile = File(filePath)

//            if (filePath != null && filePath.equqls("default.jpg"))
//                File(uploadDir + "/" + userFile.getFile().delete())
            val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("존재하지 않는 게시물입니다.")



        }
    }
}
//    private val uploadDir: Path,
//    private val storageService: StorageService
//    ) : StorageService {
//
//    init {
//        // 생성자에서 디렉토리가 없으면 생성
//        Files.createDirectories(uploadDir)
//    }
//
//    override fun init(): CommandLineRunner {
//        // 초기화 작업 수행
//        // 예를 들어, 디렉토리 내의 기존 파일들을 로딩할 수 있습니다.
//        val uploadDir = Paths.get("uploads")  // 여기서 uploadDir을 설정합니다.
//        return CommandLineRunner {
//            storageService.init()
//        }
//    }
//
//
//    override fun store(file: MultipartFile, extension: String): String {
//        // 파일 저장 구현
//        val fileName = "${System.currentTimeMillis()}.$extension"
//        val targetLocation = uploadDir.resolve(fileName)
//        Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
//        return fileName
//    }
//
//}


// 빈 등록하고
//@Service
//class FileStorageServiceImpl: StorageService {
////    override fun init() {
////        TODO("Not yet implemented")
////    }
////
////    override fun store(file: MultipartFile, extension: String): String {
////        TODO("Not yet implemented")
////    }
////
////    override fun deleteAll() {
////        TODO("Not yet implemented")
////    }


