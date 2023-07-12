package com.board.service;


import com.board.entity.DTO.MemberRegisterDTO;
import com.board.entity.member.Member;
import com.board.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member register(MemberRegisterDTO dto) {  //회원가입 로직(암호화)
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        return memberRepository.save(dto.toEntity());
    }

    //회원 목록
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    //회원 고유 number로 Member찾기
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    //회원 아이디로 Member 찾기
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
