package com.board.entity.member;

import com.board.entity.member.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //회원 고유 number

    @Column(nullable = false, length = 30, unique = true)
    private String username;  //회원 id

    @Column(nullable = false, length = 100)
    private String password;   //비밀번호

    @Column(nullable = false, length = 50)
    private String email;  //이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;   //회원 권한

    public void updateMemberData(String password, String email) {
        this.password = password;
        this.email = email;
    }


}