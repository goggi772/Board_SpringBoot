package com.board.service;

import com.board.entity.member.Member;
import com.board.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByUsername(username);
        System.out.println(member.isPresent());
        if (member.isPresent()) {
            return new MemberDetails(member.get());

        } else throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");

//        return new MemberDetails(member);
    }

}
