package com.board.entity.repository;

import com.board.entity.DTO.BoardUpdateDTO;
import com.board.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    //게시글을 수정할 때 실제 db에 적용되는 쿼리를 사용한 update로직 -> Dirty Checking으로 구현
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE Board SET title = :#{#boardUpdateDTO.title}, " +
//            "content = :#{#boardUpdateDTO.content}, " +
//            "modified_time = NOW() " +
//            "WHERE Board.id = :#{#boardUpdateDTO.id}", nativeQuery = true)
//    void updateBoard(@Param(value = "boardUpdateDTO")BoardUpdateDTO boardUpdateDTO);

}
