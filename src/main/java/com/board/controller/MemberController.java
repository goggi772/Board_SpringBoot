package com.board.controller;

import com.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    /*@GetMapping("/")
    public String member_home(Model model) {
    }*/
}
