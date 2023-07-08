package com.board.DTO;

import com.board.entity.member.Member;
import com.board.entity.member.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRegisterDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Role role;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
