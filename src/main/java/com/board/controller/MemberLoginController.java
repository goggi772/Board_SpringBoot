package com.board.controller;

import com.board.entity.DTO.MemberRegisterDTO;
import com.board.entity.member.Member;
import com.board.service.MemberService;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MemberLoginController {

    private final MemberService memberService;


    @GetMapping("/")
    public String home() {
        return "redirect:/board/list";
    }


    @GetMapping("/login/action")  //로그인 실패 시 error와 exception 값을 받아 에러메시지 출력
    public String getLoginPage(Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login2";
    }

    @GetMapping("/login")
    public String login_page() {
        return "login2";
    }

    @GetMapping("/login/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @PostMapping("/login/register/join")  //회원가입
    public Member join(@ModelAttribute MemberRegisterDTO dto) {
        return memberService.register(dto);
    }

    /*@GetMapping("/login/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "show";
    }*/
}
