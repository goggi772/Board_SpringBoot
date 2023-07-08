package com.board.userhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure (HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "Invalid Email or Password";

        if (exception instanceof DisabledException) {
            msg = "DisableException account";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "CredentialsExpiredException account";
        } else if (exception instanceof BadCredentialsException) {
            msg = "BadCredentialsException account";
        }

        log.info("failureHandler : " + msg);

        msg = URLEncoder.encode(msg, "UTF-8"); /* 한글 인코딩 깨지는 문제 방지 */
        setDefaultFailureUrl("/login?error=true&exception=" + msg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
