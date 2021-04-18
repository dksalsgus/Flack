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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
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

    private UserRepository userRepository;

    private Long userNo;
    private String userEmail;
    private String userPw;
    private String userPhone;

    @BeforeAll
    void setUp() {
        userNo = 1L;
        userEmail = "userEmail";
        userPw = "userPw";
        userPhone = "010-0000-0000";
    }

    @Test
    @Order(1)
    @Transactional
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
        log.info("saved userPhone : {}", saveUser);
    }

    @Test
    @Order(2)
    public void 회원정보_조회() throws NotFoundException {
        // set
        UserDetailResponse userDetailResponse = new UserDetailResponse();

        userDetailResponse.setUserPw(userPw);
        userDetailResponse.setUserPw(userEmail);
        userDetailResponse.setUserPw(userPhone);

        // read
        User getUser = userService.userInfo(userNo);

        // then
        assertThat(getUser.getUserNo()).isEqualTo(1L);
        assertThat(getUser.getUserPw()).isEqualTo(userPw);
        assertThat(getUser.getUserEmail()).isEqualTo(userEmail);
        assertThat(getUser.getUserPhone()).isEqualTo(userPhone);

        log.info("select UserInfo : " + getUser);
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

        log.info("update UserPhone : {}", updateUser);
    }

    @Test
    @Order(4)
    public void 회원탈퇴() throws NotFoundException {
        // select
        Optional<User> deleteUser = userRepository.findById(1L);

        deleteUser.ifPresent(selectedUser -> {
            userRepository.delete(selectedUser);
            log.info("delete User : " + deleteUser);
        });
        // ifPresent : 특정 결과를 반환하는 대신 Optional 객체가 감싸고 있는 값이 존재할 경우에만
        //             실행될 로직을 함수형 인자로 넘긴다.
    }

}
