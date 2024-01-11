package com.sparta.wangnyang.domain.board.service

import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.domain.board.dto.CreateBoardRequest
import com.sparta.wangnyang.domain.board.dto.UpdateBoardRequest
import com.sparta.wangnyang.domain.board.repository.BoardRepository
import com.sparta.wangnyang.entity.Board
import com.sparta.wangnyang.entity.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BoardServiceImpl(
        private val boardRepository: BoardRepository
) : BoardService {

    override fun getAllBoardList(): List<BoardResponse> {
        return boardRepository.findAll().map { it.toResponse() }
    }


    override fun getBoardById(boardId: Long): BoardResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시물이 존재하지 않습니다.")
        return board.toResponse()
    }

    // 게시물 생성
    @Transactional
    override fun createBoard(request: CreateBoardRequest): BoardResponse {

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
        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시물이 존재하지 않습니다.")
        val (title, mainText, writer) = request

        //본인 게시글이 아니면 에러를 발생시킨다.
        if (userId != board.userId && writer != board.userId) throw Exception("해당 게시물의 작성자가 아닙니다.")

        board.title = title
        board.mainText = mainText
        board.userId = writer

        return boardRepository.save(board).toResponse()
    }


    // 게시뭏 삭제
    @Transactional
    override fun deleteBoard(userId: String, boardId: Long) {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시물이 존재하지 않습니다.")

        if (board.userId != userId) throw Exception("해당 게시물의 작성자가 아닙니다.")

        boardRepository.delete(board)
    }

}

