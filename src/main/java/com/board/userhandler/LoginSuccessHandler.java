package com.board.userhandler;

import com.board.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor //@nonnull이나 초기화 되지 않은 final 필드에 대해 생성자를 생성해줌
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

//        memberRepository.updateUserLastLogin(authentication.getName(), LocalDateTime.now());
        setDefaultTargetUrl("/");

        super.onAuthenticationSuccess(request, response, authentication);
        
    }
}
