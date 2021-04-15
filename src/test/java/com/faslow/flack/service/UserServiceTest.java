package com.faslow.flack.service;

import com.faslow.flack.entity.dto.user.UserDetailResponse;
import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.user.UserUpdateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

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

    @Test
    @Order(2)
    public void 회원정보_조회() throws NotFoundException {
       //given
       UserDetailResponse userDetailResponse = new UserDetailResponse();

       userDetailResponse.getUserNo();
       userDetailResponse.getUserPw();
       userDetailResponse.getUserEmail();
       userDetailResponse.getUserPhone();

       //when
       UserDetailResponse userInfo = userService.userInfo(1L);

       //then
       assertThat(userInfo.getUserNo()).isEqualTo(1l);
       assertThat(userInfo.getUserPw()).isEqualTo(userDetailResponse.getUserPw());
       assertThat(userInfo.getUserPhone()).isEqualTo(userDetailResponse.getUserEmail());
       assertThat(userInfo.getUserPhone()).isEqualTo(userDetailResponse.getUserPhone());

       log.info("get userNo : {}", userInfo.getUserNo());
       log.info("get UserPw : {}", userInfo.getUserPw());
       log.info("get UserEmail : {}", userInfo.getUserEmail());
       log.info("get UserPhone : {}", userInfo.getUserPhone());
    }

    @Test
    @Order(3)
    public void 회원정보_수정() throws NotFoundException {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setUserPw("updatePw");
        userUpdateRequest.setUserPhone("updatePhone");
        User updateUser = userService.update(1L, userUpdateRequest);
        assertThat(updateUser.getUserNo()).isEqualTo(1L);
        assertThat(updateUser.getUserPw()).isEqualTo(userUpdateRequest.getUserPw());
        assertThat(updateUser.getUserPhone()).isNotEqualTo(this.userPhone);

        log.info("update UserNo : {}", updateUser.getUserNo());
        log.info("update UserPw : {}", updateUser.getUserPw());
        log.info("update UserPhone : {}", updateUser.getUserPhone());
    }
}
