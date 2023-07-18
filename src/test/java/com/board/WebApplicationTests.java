package com.board;

import com.board.entity.DTO.MemberUpdateDTO;
import com.board.entity.member.Member;
import com.board.entity.member.Role;
import com.board.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

@SpringBootTest
@AutoConfigureMockMvc
class WebApplicationTests {

	@Autowired
	private MemberService memberService;

	@Test
	void updateUserDataTest() {
//		Member member = new Member(1L, "user", "1234", "zxcv", Role.USER);

		MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO("user", "zxcv", "4321", "pwd");


		Assertions.assertThrows(BadCredentialsException.class, ()->
		{memberService.updateMemberData(memberUpdateDTO);});
	}

}
