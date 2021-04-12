package com.faslow.flack;

import com.faslow.flack.dto.UserDto;
import com.faslow.flack.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    String userEmail = "email test";
    String userPw = "userpw test";
    String userPhone = "000-0000-0000";

    @Test
    public void 회원가입() throws Exception{

        UserDto userDto = new UserDto();
        userDto.setUserEmail(userEmail);
        userDto.setUserPw(userPw);
        userDto.setUserPhone(userPhone);

        userService.join(userDto);

        //assertj : 테스트 검증 라이브러리의 검증 메소드
        //isEqualTo : assertj의 비교 메소드, 값이 같을 때만 성공

        assertThat(userDto.getUserEmail()).isEqualTo(userEmail);
        assertThat(passwordEncoder.matches(userPw, userDto.getUserPw()));
        assertThat(userDto.getUserPhone()).isEqualTo(userPhone);

    }
}
