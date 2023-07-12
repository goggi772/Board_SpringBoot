package com.board.entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginDTO {  //로그인 DTO
    private String username;
    private String password;
}
