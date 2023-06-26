package com.board.config;

import com.board.service.UserService;
import com.board.userhandler.LoginFailureHandler;
import com.board.userhandler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity //security filter등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 페이지에 특정권한이 있는 유저만 접근을 허용할 경우 권한 및 인증을 미리 체크하겠다는 설정을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    @Bean
    public BCryptPasswordEncoder encryptPassword() {
        return new BCryptPasswordEncoder();
    }

    @Override //암호화해서 비교
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encryptPassword());
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login/**", "/js/**", "/css/**", "/image/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/action") // 해당 URL로 요청이 오면 스프링 시큐리티가 가로채서 로그인처리를 한다. -> loadUserByName
                .successHandler(loginSuccessHandler) // 성공시 요청을 처리할 핸들러
                .failureHandler(loginFailureHandler) // 실패시 요청을 처리할 핸들러
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL
                .logoutSuccessUrl("/login") // 성공시 리턴 URL
                .invalidateHttpSession(true) // 인증정보를 지우하고 세션을 무효화
                .deleteCookies("JSESSIONID","remember-me") // JSESSIONID, remember-me 쿠키 삭제
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(1) // 세션 최대 허용 수 1, -1인 경우 무제한 세션 허용
                .maxSessionsPreventsLogin(false) // true면 중복 로그인을 막고, false면 이전 로그인의 세션을 해제
                .expiredUrl("/login?error=true&exception=Have been attempted to login from a new place. or session expired") // 세션이 만료된 경우 이동 할 페이지를 지정
                .and()
                .and().rememberMe() // 로그인 유지
                .alwaysRemember(false) // 항상 기억할 것인지 여부
                .tokenValiditySeconds(43200) // in seconds, 12시간 유지
                .rememberMeParameter("remember-me");
    }
}
