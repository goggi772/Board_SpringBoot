package com.board.config;


import com.board.service.MemberDetailsService;
import com.board.userhandler.CustomAuthFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //security filter등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 페이지에 특정권한이 있는 유저만 접근을 허용할 경우 권한 및 인증을 미리 체크하겠다는 설정을 활성화
public class SecurityConfig {

    private final CustomAuthFailureHandler customAuthFailureHandler;
    private final MemberDetailsService memberDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration, AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/board/write").authenticated()   //게시글 작성은 로그인 필요
                .antMatchers( "/","/board/**", "/login/**", "/main/**", "/js/**", "/css/**", "/image/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/action")   //로그인 실패 시 error, exception 파라미터 전송
                .defaultSuccessUrl("/board/list")
                .failureHandler(customAuthFailureHandler)// 실패시 요청을 처리할 핸들러
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL
                .logoutSuccessUrl("/board/list"); // 성공시 리턴 URL

        return http.build();

    }

    public DaoAuthenticationProvider daoAuthenticationProvider() { //아이디가 맞지 않을 때 UserNotFoundException을 발생하게 하는 메소드
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(memberDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

}
