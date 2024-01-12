package com.sparta.wangnyang.domain.comment.service

import com.sparta.wangnyang.domain.board.repository.BoardRepository
import com.sparta.wangnyang.domain.comment.dto.CommentResponse
import com.sparta.wangnyang.domain.comment.dto.CreateCommentRequest
import com.sparta.wangnyang.domain.comment.dto.UpdateCommentRequest
import com.sparta.wangnyang.domain.comment.repository.CommentRepository
import com.sparta.wangnyang.entity.Comment
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service // 이 클래스는 Spring에서 서비스 역할을 한다고 붙여주는 어노테이션
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val boardRepository: BoardRepository,
): CommentService {

//    override fun getComment(): List<CommentResonse> {
//        return commentRepository.findAll().map {CommentResponse.fromEntity(it)}
//    }

        override fun getComment(): Page<Comment> {
            val pageable : Pageable = PageRequest(0, 5, Sort.by(Sort.Direction.DESC, "userId"))
            commentRepository.
//            return commentRepository.findAll().map {CommentResponse.fromEntity(it)}
    }

    // 모든 댓글을 가져와서 CommentResponse 객체로 매핑해서 리스트로 반환한다
    @Transactional
    override fun createComment(boardId:Long, createCommentRequest: CreateCommentRequest): CommentResponse {
        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw NoSuchElementException("존재하지 않는 게시물입니다.")
        val comment = Comment(
            text = createCommentRequest.text,
            userId = createCommentRequest.writer,
            board = board,
        )

        val result = comment.let {
            commentRepository.save(it)
        }

        return result.let {
            CommentResponse.fromEntity(it)
        }

    }
    // 새로운 댓글을 생성하고 저장한 뒤, 해당 댓글의 정보를 CommentResponse로 변환해서 반환한다.
    // boardId를 이용해 데이터베이스에서 해당 게시물을 찾아온다.
    // Comment 객체를 생성하고, commentRepository.save(comment)를 통해 데이터베이스에 저장한다.
    // 저장된 댓글을 CommentResponse.fromEntity(result)를 통해 CommentResponse객체로 변환해서 반환한다.


    @Transactional
    override fun updateComment(userId:String,  commentId: Long, updateCommentRequest: UpdateCommentRequest): CommentResponse {
        val foundComment = commentId.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception ("target comment is not found")

        if(foundComment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")

        foundComment.userId = updateCommentRequest.writer
        foundComment.text = updateCommentRequest.text

        val result = commentRepository.save(foundComment)
        return CommentResponse.fromEntity(result)
    }
    // 특정 댓글을 업데이트한 뒤, 업데이트된 댓글의 정보를 CommentResponse로 변환해서 반환한다.
    // boardId를 이용해 데이터베이스에서 해당 댓글을 찾아온다.
    // 찾아온 댓글의 writer와 text를 updateCommentRequest에서 받은 값으로 업데이트한다.
    // 저장된 댓글을 CommentResponse.fromEntity(result)를 통해 CommentResponse 객체로 변환하고 반환한다.

    @Transactional
    override fun deleteComment(userId:String,  commentId: Long) {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw NoSuchElementException("target comment is not found")

        if(foundComment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")
        commentRepository.delete(foundComment)
    }
    // 특정 댓글을 삭제한다.
    // commentId를 이용해 데이터베이스에서 해당 댓글을 찾아온다.
    // 찾아온 댓글을 commentRepository.delete(foundComment)를 통해 데이터베이스에서 삭제한다.

    @Transactional
    override fun createSubComment(
        boardId: Long,
        parentId: Long,
        createCommentRequest: CreateCommentRequest
    ): CommentResponse {
        val parentComment = commentRepository.findByIdOrNull(parentId)
            ?: throw NoSuchElementException("원 댓글을 찾을 수 없습니다.")

        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw NoSuchElementException("존재하지 않는 게시물입니다.")

        val subComment = Comment(
            text = createCommentRequest.text,
            userId = createCommentRequest.writer,
            board = board,
            parent = parentComment
        )

        val result = commentRepository.save(subComment)

        return CommentResponse.fromEntity(result)
    }

    @Transactional
    override fun updateSubComment(
        userId: String,
        commentId: Long,
        updateCommentRequest: UpdateCommentRequest
    ): CommentResponse {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw NoSuchElementException("대댓글을 찾을 수 없습니다.")

        if (foundComment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")

        foundComment.userId = updateCommentRequest.writer
        foundComment.text = updateCommentRequest.text

        val result = commentRepository.save(foundComment)
        return CommentResponse.fromEntity(result)
    }

    @Transactional
    override fun deleteSubComment(userId: String, commentId: Long) {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw NoSuchElementException("대댓글을 찾을 수 없습니다.")

        if (foundComment.userId != userId) throw Exception("작성자가 일치하지 않습니다.")
        commentRepository.delete(foundComment)
    }
}