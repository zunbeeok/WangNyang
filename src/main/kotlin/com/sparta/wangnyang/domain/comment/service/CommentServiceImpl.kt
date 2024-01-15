package com.sparta.wangnyang.domain.comment.service

import com.sparta.wangnyang.domain.board.repository.BoardRepository
import com.sparta.wangnyang.domain.comment.dto.*
import com.sparta.wangnyang.domain.comment.repository.CommentRepository
import com.sparta.wangnyang.domain.comment.repository.SubCommentRepository
import com.sparta.wangnyang.entity.Board
import com.sparta.wangnyang.entity.Comment
import com.sparta.wangnyang.entity.SubComment
import com.sparta.wangnyang.entity.toResponse
import io.jsonwebtoken.security.SignatureException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service // 이 클래스는 Spring에서 서비스 역할을 한다고 붙여주는 어노테이션
class CommentServiceImpl(

    private val commentRepository: CommentRepository,
    private val subCommentRepository: SubCommentRepository,
    private val boardRepository: BoardRepository



): CommentService {
//    override fun getComments(boardId: Long): List<CommentResponse> {
//        val comments = boardRepository.findByIdOrNull(boardId) ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")
//        return comments.findAll.map { it.toResponse() };
//        //db comment로 boardId인거 전부 다가져오면 되는거아닌가?
//    }

    override fun getComment(parentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(parentId) ?: throw  Exception("댓글이 조회되지 않습니다.")
        return  comment.toResponse();
    }

    @Transactional
    override fun createComment(userId: String, boardId: Long, request: CreateCommentRequest): CommentResponse {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        val board = boardRepository.findByIdOrNull(boardId) ?: throw Exception("게시글이 조회되지 않습니다.")


        return commentRepository.save(
                Comment(
                        userId = userId,
                        board = board,
                        text = request.text,
                )
        ).toResponse();
    }

    @Transactional
    override fun updateComment(userId: String, parentId: Long, request: UpdateCommentRequest): CommentResponse {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        val comment = commentRepository.findByIdOrNull(parentId) ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")

        if (comment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")

        comment.id = parentId
        comment.text = request.text

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment(userId: String, boardId: Long, parentId: Long) {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        val board = boardRepository.findByIdOrNull(boardId)?: throw Exception("게시글이 조회되지 않습니다.")
        val comment = commentRepository.findByIdOrNull(parentId) ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")

        if (comment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")
        board.deleteComment(comment)
        boardRepository.save(board)
    }

    // 부모 댓글 단건 조회 - 부모 아이디 기반으로 조회, 응답으로 여러개의 자식 댓글들이 있을 수도 있어서 findByParent()의 반환 타입은 List<Comment> /    @Transactional
    override fun getSubComments(parentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(parentId) ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")

        // 해당하는 부모 댓글만 반환 받아오는 메서드.
        return comment.toResponse()
    }


    @Transactional
    override fun createSubComment(
            userId:String,
        parentId: Long,
        request: CreateSubCommentRequest
    ): SubCommentResponse {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        val comment = commentRepository.findByIdOrNull(parentId)
            ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")


        val subComment = SubComment(
//            parentId = request.parentId,
            userId = request.writer,
            text = request.text,
            comment = comment
        )
        comment.createSubComment(subComment)
        commentRepository.save(comment)
        return subComment.toResponse()
    }


    @Transactional
    override fun updateSubComment(
            userId:String,
        parentId: Long,
        subCommentId: Long,
        request: UpdateSubCommentRequest
    ): CommentResponse {

/// !!!!!!!!!!!!!!!!!!!!!!유저 권한은 다시 붙여주세요 준홍님!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//        val userId = request.writer
    val comment = commentRepository.findByIdOrNull(parentId) ?: throw Exception("원 댓글을 찾을 수 없습니다.")
    val subComment = commentRepository.findByIdOrNull(subCommentId) ?: throw Exception("원 댓글을 찾을 수 없습니다.")

        subComment.text = request.text

        return commentRepository.save(comment).toResponse()

    }



    @Transactional
    override fun deleteSubComment(userId: String, parentId: Long, subCommentId: Long) {
        if(userId == "anonymous") throw SignatureException("로그인을 해주세요.")

        val comment = commentRepository.findByIdOrNull(parentId)
            ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")

        val subComment = subCommentRepository.findByIdOrNull(subCommentId) ?: throw NoSuchElementException("대댓글을 찾을 수 없습니다.")

    // !!!!!!!!!!!!!!!!!!!!!!!!!작성자 권한은 준홍님이!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (comment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")
    comment.removeSubComment(subComment)
    commentRepository.save(comment)

    }
}