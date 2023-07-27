package com.board.controller;

import com.board.entity.DTO.BoardReadDTO;
import com.board.entity.DTO.BoardUpdateDTO;
import com.board.entity.DTO.BoardWriteDTO;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list") //게시글 목록 page로 보여줌
    public String board_home(Model model,
                             @RequestParam(required = false, defaultValue = "0") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer size)
            throws Exception {
        try {
            model.addAttribute("resultMap", boardService.findAll(page, size));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "board_home";
    }

    @GetMapping("/view") //특정 게시글 title, content, writeusername을 보여줌
    public String getBoardViewPage(Model model, @ModelAttribute BoardReadDTO boardReadDTO) throws Exception {

        try {
            if (boardReadDTO.getId() != null) {
                model.addAttribute("info", boardService.findById(boardReadDTO.getId()));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "board_view";
    }


    @GetMapping("/update")  //title, content 내용 수정 화면 보여줌
    public String getBoardUpdatePage(Model model, @ModelAttribute BoardReadDTO boardReadDTO) throws Exception {
        try {
            if (boardReadDTO.getId() != null) {
                model.addAttribute("info", boardService.findById(boardReadDTO.getId()));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "board_update";
    }

    @PostMapping("/update/action")  //실제로 수정하고 modifying버튼 클릭시 update하게 되는 controller
    public String board_update(Model model, @ModelAttribute BoardUpdateDTO boardUpdateDTO) {
        boardService.update_content(boardUpdateDTO);
        model.addAttribute("info", boardService.findById(boardUpdateDTO.getId()));
        return "board_view";
    }

    @GetMapping("/write")  //게시글 작성 화면
    public String write_page() {
        return "board_write";
    }


    @PostMapping("/write/action")  //게시글 작성 로직
    public String write(@ModelAttribute BoardWriteDTO boardWriteDTO) {
        boardService.write(boardWriteDTO);
        return "redirect:/board/list";
    }


    @PostMapping("/delete")  //게시글 삭제 로직
    public String delete(@ModelAttribute BoardReadDTO boardReadDTO) {
        boardService.delete_Id(boardReadDTO);
        return "redirect:/board/list";
    }
}
