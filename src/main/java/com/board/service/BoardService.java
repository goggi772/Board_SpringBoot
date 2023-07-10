package com.board.service;

import com.board.DTO.BoardReadDTO;
import com.board.DTO.BoardWriteDTO;
import com.board.entity.board.Board;
import com.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardReadDTO> findAll() {
        return boardRepository.findAll().stream().map(BoardReadDTO::new).collect(Collectors.toList());
    }

    public Long write(BoardWriteDTO boardWriteDTO) {
        return boardRepository.save(boardWriteDTO.toEntity()).getId();
    }

    public int update_content(BoardWriteDTO boardWriteDTO) {
//        return boardRepository.updateBoard(boardWriteDTO);
    }

    public void delete_Id(Long id) {
        boardRepository.deleteById(id);
//        boardRepository.updateBoardId(id);
    }

//    public Optional<BoardReadDTO> findByTitle(String title) {
//        return boardRepository.findByTitle(title);
//    }

}
