package com.faslow.flack.service;

import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class ProfileServiceTest {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    private String userEmail;
    private String userPw;
    private String userPhone;

    private String profileName;
    private String profileState;
    private String profilePicture;
    private User userNo;


    @BeforeAll
    void setUp() {
        userEmail = "userEmail";
        userPw = "userPw";
        userPhone = "user phone";
        profileName = "profileName";
        profileState = "profileState";
        profilePicture = "profilePicturePath";
    }


    @Test
    @Order(1)
    void 프로필_생성() throws Exception {
        // 회원가입
        UserDto userDto = new UserDto();
        userDto.setUserEmail(userEmail);
        userDto.setUserPw(userPw);
        userDto.setUserPhone(userPhone);
        User saveUser = userService.join(userDto);
        // 프로필 생성
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileName(profileName);
        profileDto.setProfileState(profileState);
        profileDto.setProfilePicture(profilePicture);
        profileDto.setUserNo(saveUser);

        Profile createdProfile = profileService.createProfile(profileDto);
        log.info("{}", createdProfile.getUser());

        assertThat(createdProfile.getProfileNo()).isEqualTo(1);
        assertThat(createdProfile.getProfileName()).isEqualTo(profileName);
        log.info("Created Profile : {}", createdProfile);
    }

    @Test
    @Order(2)
    @Transactional
    void 프로필_조회() throws NotFoundException {
        Profile findProfile = profileService.profileDetails(1L);
        assertThat(findProfile.getUser().getUserNo()).isEqualTo(1L);
        log.info("find Profile : {}", findProfile);
    }

}
