package com.board.controller;

import com.board.DTO.MemberRegisterDTO;
import com.board.entity.member.Member;
import com.board.service.MemberDetails;
import com.board.service.MemberService;
import lombok.RequiredArgsConstructor;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @GetMapping("/login/action")
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
    @PostMapping("/login/register/join")
    public Member join(@ModelAttribute MemberRegisterDTO dto) {
        return memberService.register(dto);
    }

    /*@GetMapping("/login/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "show";
    }*/
}
