package com.board.service;


import com.board.entity.DTO.MemberUpdateDTO;
import com.board.entity.DTO.MemberRegisterDTO;
import com.board.entity.member.Member;
import com.board.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public void register(MemberRegisterDTO dto) {  //회원가입 로직(암호화)
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        memberRepository.save(dto.toEntity());
    }

    @Transactional
    public void updateMemberData(MemberUpdateDTO requestDto) {
        Member member = findByUsername(requestDto.getUsername()).orElseThrow(()->
                new UsernameNotFoundException("NotFoundUserName"));
        if (bCryptPasswordEncoder.matches(requestDto.getOldPassword(), member.getPassword())) {
            member.updateMemberData(requestDto.getNewPassword(), requestDto.getEmail());
            memberRepository.save(member);
        } else throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
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
