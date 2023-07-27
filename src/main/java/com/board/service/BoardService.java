package com.board.service;

import com.board.entity.DTO.BoardReadDTO;
import com.board.entity.DTO.BoardUpdateDTO;
import com.board.entity.DTO.BoardWriteDTO;
import com.board.entity.board.Board;
import com.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(BoardWriteDTO boardWriteDTO) {  //게시글 처음 작성시 db저장 로직
        boardRepository.save(boardWriteDTO.toEntity());
    }

    public void update_content(BoardUpdateDTO dto) {   //게시글 수정 시 db update
        Board board = boardRepository.findById(dto.getId()).orElseThrow(() ->
                new IllegalArgumentException("게시물이 존재하지 않음"));
        board.updateContent(dto.getTitle(), dto.getContent());
        boardRepository.save(board);
    }

    public void delete_Id(BoardReadDTO dto) {  //게시글 삭제 시 db에서 삭제
        boardRepository.deleteById(dto.getId());
    }

    @Transactional(readOnly = true)  //pagerequest로 게시글 전체 출력
    public HashMap< String, Object > findAll(Integer page, Integer size) {

        HashMap<String, Object> resultMap = new HashMap<>();

        Page<Board> list = boardRepository.findAll(PageRequest.of(page, size, Sort.by("registerTime").descending()));

        resultMap.put("list", list.stream().map(BoardReadDTO::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());
        resultMap.put("number", list.getNumber());

        return resultMap;
    }

    public BoardReadDTO findById(Long id) {  //게시글 id로 BoardReadDTO찾기
        return boardRepository.findById(id).map(BoardReadDTO::new).orElseThrow(() ->
                new RuntimeException("id가 존재하지 않음"));
    }


}
