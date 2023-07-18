package com.board.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateDTO {  //로그인 DTO
    private String username;
    private String email;
    private String oldPassword;
    private String newPassword;
}
