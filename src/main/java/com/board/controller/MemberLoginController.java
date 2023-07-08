package com.board.controller;

import com.board.DTO.MemberRegisterDTO;
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
        return "home";
    }


    @GetMapping("/main/login/action")
    public String getLoginPage(Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login_failure";
    }

    @GetMapping("/login")
    public String login_page() {
        return "login2";
    }

    @GetMapping("main/register")
    public String register() {
        return "register";
    }

    /*@ResponseBody
    @PostMapping("/main/login/action")
    public TokenInfo login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpServletResponse response) {

        String id = memberLoginDTO.getUsername();
        String password = memberLoginDTO.getPassword();
        TokenInfo tokenInfo = memberService.login(id, password);
        *//*Cookie idCookie = new Cookie("memberId", String.valueOf(tokenInfo.getAccessToken()));
        response.addCookie(idCookie);*//*
        return tokenInfo;

    }*/

    @ResponseBody
    @PostMapping("/main/register/join")
    public Member join(@ModelAttribute MemberRegisterDTO dto) {
        return memberService.register(dto);
    }

    @GetMapping("/main/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "show";
    }
}
