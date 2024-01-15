package com.sparta.wangnyang.domain.board.service

import com.sparta.wangnyang.domain.board.dto.BoardListResponse
import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.domain.board.dto.CreateBoardRequest
import com.sparta.wangnyang.domain.board.dto.UpdateBoardRequest
import com.sparta.wangnyang.domain.board.repository.BoardRepository
import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest
import com.sparta.wangnyang.entity.Board
import com.sparta.wangnyang.entity.Comment
import com.sparta.wangnyang.entity.toResponse
import io.jsonwebtoken.security.SignatureException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.MethodArgumentNotValidException


@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository

) : BoardService {

//    override fun getAllBoardList(): List<BoardResponse> {
//        return boardRepository.findAll().map { it.toResponse() }
//    }

    override fun getAllBoardList(): Page<BoardListResponse> {
        // 단일 페이지 1번 페이지의 데이터 개수를 5개로 제한, 최신 등록한 게시글이 먼저 오도록 아이디별로 내림차순

        val pageable: Pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"))
        // Board 테이블의 데이터를 모두 조회. 이때 조회하면서 변수 pageable의 페이징 설정 of(인자들) 적용
//        return boardRepository.findByIdOrderByIdDesc(pageable)
        return boardRepository.findAllByOrderByCreatedAtDesc(pageable).map {
            BoardListResponse(it)
        };
    }


    override fun getBoardById(boardId: Long): BoardResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시물이 존재하지 않습니다.")

        return board.toResponse()
    }


    // 게시물 생성
    @Transactional
    override fun createBoard(userId: String,request: CreateBoardRequest): BoardResponse {

        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        //
        return boardRepository.save(
             Board(
                 title = request.title,
                 mainText = request.mainText,
                 userId = request.writer
                )
        ).toResponse()
    }


    // 게시물 수정
    @Transactional
    override fun updateBoard(userId: String, boardId: Long, request: UpdateBoardRequest): BoardResponse {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시물이 존재하지 않습니다.")
        val (title, mainText) = request

        //본인 게시글이 아니면 에러를 발생시킨다.
        if (userId != board.userId) throw Exception("해당 게시물의 작성자가 아닙니다.")

        board.title = title
        board.mainText = mainText

        return boardRepository.save(board).toResponse()
    }



    // 게시물 삭제
    @Transactional
    override fun deleteBoard(userId: String, boardId: Long) {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")
        
        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시물이 존재하지 않습니다.")

        if (board.userId != userId) throw Exception("해당 게시물의 작성자가 아닙니다.")

        boardRepository.delete(board)
    }




}

