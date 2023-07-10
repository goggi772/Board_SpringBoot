package com.board.entity.repository;

import com.board.DTO.BoardReadDTO;
import com.board.DTO.BoardWriteDTO;
import com.board.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

//    Optional<BoardReadDTO> findByTitle(String title);


    /*@Transactional
    @Modifying
    @Query(value = "UPDATE board SET TITLE = :boardWriteDTO.title, " +
            "CONTENT = :boardWriteDTO.content, " +
            "MODIFIED_TIME = NOW() " +
            "WHERE ID = :boardWriteDTO.id")
    public int updateBoard(@Param("boardWriteDTO") BoardWriteDTO boardWriteDTO);*/
//    public int updateBoard(@Param("id") Long id, @Param("title") String title, @Param("content") String content);

    /*String UPDATE_BOARD_ID = "UPDATE board " +
            "SET id = id - 1 " +
            "WHERE id > :id";

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD_ID, nativeQuery = true)
    public void updateBoardId(@Param("id") Long id);*/
}
