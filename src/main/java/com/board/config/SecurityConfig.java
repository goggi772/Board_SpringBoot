package com.board.config;


import com.board.service.MemberDetailsService;
import com.board.userhandler.CustomAuthFailureHandler;
import com.board.userhandler.LoginFailureHandler;
import com.board.userhandler.LoginSuccessHandler;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //security filter등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 페이지에 특정권한이 있는 유저만 접근을 허용할 경우 권한 및 인증을 미리 체크하겠다는 설정을 활성화
public class SecurityConfig {

    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
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

    /*@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어주는 게 좋다.)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()            // auth의 요청이 들어오면
                .antMatchers("/", "/main/**", "/js/**", "/css/**", "/image/**").permitAll()   // auth쪽으로 들어 오는건 요청없이 누구나 들어올수 있다.
                .anyRequest()      // 위의 것이 아닌 다른 모든 요청은
                .authenticated()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증이 되어야한다.
                .and()
                .formLogin()
                .loginPage("/main/login_page")
                .loginProcessingUrl("/main/login/action") //submit을 받을 url주소
//                .successHandler(loginSuccessHandler) // 성공시 요청을 처리할 핸들러
                .failureHandler(loginFailureHandler) // 실패시 요청을 처리할 핸들러
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }*/


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/","/board/**", "/login/**", "/main/**", "/js/**", "/css/**", "/image/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/action")
                .defaultSuccessUrl("/")
//                .loginPage("/main/login_page")
//                .successHandler(loginSuccessHandler) // 성공시 요청을 처리할 핸들러
                .failureHandler(customAuthFailureHandler) // 실패시 요청을 처리할 핸들러
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL
                .logoutSuccessUrl("/"); // 성공시 리턴 URL

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
