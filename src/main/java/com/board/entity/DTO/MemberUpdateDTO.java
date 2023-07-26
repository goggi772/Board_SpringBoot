package com.board.entity.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class MemberUpdateDTO {  //로그인 DTO
    private String username;
    private String email;
    private String oldPassword;
    private String newPassword;
}
