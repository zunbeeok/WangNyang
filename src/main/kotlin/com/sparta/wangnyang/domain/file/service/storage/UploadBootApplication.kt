//package com.sparta.wangnyang.domain.file.service.storage
//
//import org.springframework.boot.CommandLineRunner
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.context.annotation.Bean
//import java.nio.file.Paths
//
//// 추가적으로 커스텀 하기 위해서
//@SpringBootApplication
//class UploadBootApplication {
//
////    fun init(storageService: StorageService): CommandLineRunner {
////        return CommandLineRunner {
////            storageService.deleteAll()
////            storageService.init()
////        }
////    }
//
//    fun init(storageService: StorageService): CommandLineRunner {
//        val uploadDir = Paths.get("uploads")  // 여기서 uploadDir을 설정합니다.
//        return CommandLineRunner {
//            storageService.deleteAll()
//            storageService.init()
//        }
//    }
//
//
//}