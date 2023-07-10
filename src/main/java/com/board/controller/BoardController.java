package com.board.controller;

import com.board.DTO.BoardWriteDTO;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String board_home() {
        return "board_home";
    }

    @GetMapping("/update")
    public int board_update(@ModelAttribute BoardWriteDTO boardWriteDTO) {
        return boardService.update_content(boardWriteDTO);
    }

    @GetMapping("/write")
    public Long write(@ModelAttribute BoardWriteDTO boardWriteDTO) {
        return boardService.write(boardWriteDTO);
    }
}
