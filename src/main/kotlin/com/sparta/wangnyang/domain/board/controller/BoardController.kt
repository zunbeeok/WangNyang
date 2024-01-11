package com.sparta.wangnyang.domain.board.controller

import com.sparta.wangnyang.domain.board.dto.BoardResponse
import com.sparta.wangnyang.domain.board.dto.CreateBoardRequest
import com.sparta.wangnyang.domain.file.dto.FileResponse
import com.sparta.wangnyang.domain.board.dto.UpdateBoardRequest
import com.sparta.wangnyang.domain.board.service.BoardService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RequestMapping("/board")
@RestController
class BoardController(
    private val boardService: BoardService,
) {

    @GetMapping()
    fun getBoardList(): ResponseEntity<List<BoardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getAllBoardList())
    }


    @GetMapping("/{boardId}")
    fun getBoard(@PathVariable boardId: Long): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getBoardById(boardId))

    }

    @PostMapping
    fun createBoard(@RequestBody createBoardRequest: CreateBoardRequest): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.createBoard(createBoardRequest))
    }



    @PutMapping("/{boardId}")
    fun updateBoard(
        @PathVariable boardId: Long,
        @RequestBody updateBoardRequest: UpdateBoardRequest
    )
            : ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.updateBoard(boardId, updateBoardRequest))
    }

    @DeleteMapping("/{boardId}")
    fun deleteBoard(@PathVariable boardId: Long): ResponseEntity<Unit> {
        boardService.deleteBoard(boardId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}

