package com.board.controller;

import com.board.entity.DTO.ErrorResponse;
import com.board.entity.DTO.MemberRegisterDTO;
import com.board.entity.DTO.MemberUpdateDTO;
import com.board.entity.member.Member;
import com.board.service.MemberService;
import com.board.userhandler.NotEqualPasswordException;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;

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

    @PostMapping("/login/register/join")  //회원가입
    public String join(@ModelAttribute MemberRegisterDTO dto) {
        memberService.register(dto);
        return "redirect:/login";
    }

    @PostMapping("/login/userPage")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("member", memberService.findAllByUsername(principal.getName()));
        return "userDataPage";
    }

    @PostMapping("/login/userModifyPage")
    public String userModifyPage(Model model, Principal principal) {
        model.addAttribute("member", memberService.findAllByUsername(principal.getName()));
        return "userModifyPage";
    }


    @PostMapping("/login/userPage/update")
    public String userDataUpdate(Model model,
                                 @ModelAttribute MemberUpdateDTO memberUpdateDTO){
//        model.addAttribute("error", error);
//        model.addAttribute("exception", exception);
        try {
            memberService.updateMemberData(memberUpdateDTO);
            model.addAttribute("member", memberService.findAllByUsername(memberUpdateDTO.getUsername()));
            System.out.println("success");
            return "userDataPage";
        } catch (NotEqualPasswordException e){
            model.addAttribute("member", memberService.findAllByUsername(memberUpdateDTO.getUsername()));
            model.addAttribute("error", "비밀번호가 맞지 않습니다.");
            System.out.println("fail");
            return "userModifyPage";
        }
        /*memberService.updateMemberData(memberUpdateDTO);
        System.out.println("success");
        return "redirect:/login/userPage";*/
    }

    /*@GetMapping("/login/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "show";
    }*/
}
