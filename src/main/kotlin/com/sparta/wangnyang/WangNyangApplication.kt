package com.sparta.wangnyang

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WangNyangApplication

fun main(args: Array<String>) {
    runApplication<WangNyangApplication>(*args)
}


// Spring이 runApplication 함수가 실행되면 하는 일

// 모든 클래스에 달린 Annotation을 분석함

// CommentController에 RestController가 달려있네?
// 서버 요청이 들어오면 여기다가 보내줘야 겠다

// CommentController를 생성하려고 봤더니 CommentService가 필요하네?

// CommentService 콩을 찾아볼까?

// CommentServiceImpl에 Service 어노테이션이 달려있네? 이게 콩이군

// CommentServiceImpl를 생성하려고 봤더니 CommentRepository가 필요하네?

// CommentRepository 콩을 찾아보니 JpaRepository가 상속되어있꾼! 필요한 걸 알아서 만들어줘야겠따

// 이제 필요한 게 다 만들어졌으니 앱 실행!

// 결국 필요한 콩들을 죄다 찾아서 만들어서 자동으로 필요한 곳에 넣어주는 일을 함


// --- 제어 역전이 없다면?

// val repository = CommentRepository()
// val service = CommentServiceImpl(repository)
// val controller = CommentController(service)
// val application = runApplication(controller, ...)
// application.run()

// 이래야 하는데

// 제어 역전 덕분에

// runApplication() 딸깍 가능