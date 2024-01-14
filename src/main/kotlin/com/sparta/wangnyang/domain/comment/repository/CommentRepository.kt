package com.sparta.wangnyang.domain.comment.repository

import com.sparta.wangnyang.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

//    fun findByParent(parentComment: Comment): List<Comment>
//fun findCommentByDepth(commentId: Long, depth: Int): List<Comment>
   // 특정 원댓글에 해당하는 모든 대댓글 가져오기. (parent에 해당하는 거)



//    select
//    데이터를 어떻게 보여줄것이가 ( 계층구조를 여기서 만들 수 있다.)
//    from
//    어떤데이터를 참고할것인가.
//    where
//    어떤 조건을 걸것인가.
//    order by{
//        desc  asc
    }

//    @Query(
//       value = "SELECT *  FROM "테이블명"  WHERE parentId IN (SELECT id FROM comment WHERE board_Id = :boardId)ORDER By id DESC",
//                nativeQuery = true
//    )
//    List<BoardResponse> Get

