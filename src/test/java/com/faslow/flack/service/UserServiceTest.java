package com.faslow.flack.service;

import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private String userEmail;
    private String userPw;
    private String userPhone;

    @BeforeAll
    void setUp() {
        userEmail = "doongji.team@gmail.com";
        userPw = "userpw test";
        userPhone = "010-0000-0000";
    }

    @Test
    @Order(1)
    public void 회원가입() throws Exception {
        // 웹 Input값(DTO) Setup
        UserDto userDto = new UserDto();
        userDto.setUserEmail(userEmail);
        userDto.setUserPw(userPw);
        userDto.setUserPhone(userPhone);
        // insert
        User saveUser = userService.join(userDto);
        //assertj : 테스트 검증 라이브러리의 검증 메소드
        //isEqualTo : assertj의 비교 메소드, 값이 같을 때만 성공
        assertThat(userEmail).isEqualTo(saveUser.getUserEmail());
        assertThat(bCryptPasswordEncoder.matches(userPw, saveUser.getUserPw())).isTrue();
        assertThat(userPhone).isEqualTo(saveUser.getUserPhone());
        // 확인
        log.info("saved userEmail : {}", saveUser.getUserEmail());
        log.info("saved userPw : {}", saveUser.getUserPw());
        log.info("saved userPhone : {}", saveUser.getUserPhone());
    }
}
