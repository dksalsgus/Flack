package com.faslow.flack.service;

import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.profile.Profile;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    WorkSpaceService workSpaceService;


    private String userEmail;
    private String userPw;
    private String userPhone;

    private String workspaceName;

    private String profileName;
    private String profileState;
    private String profilePicture;


    @BeforeAll
    void setUp() throws NotFoundException {
        userEmail = "userEmail";
        userPw = "userPw";
        userPhone = "user phone";
        profileName = "profileName";
        profileState = "profileState";
        profilePicture = "profilePicturePath";

        workspaceName = "faslow";

        userService.join(new UserDto(userEmail, userPw, userPhone));
        workSpaceService.createWorkSpace(userEmail, new WorkSpaceCreateRequest(workspaceName));
    }


    @Test
    @Order(1)
    void 프로필_생성() throws Exception {
        // 프로필 생성
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileName(profileName);
        profileDto.setProfileState(profileState);
        profileDto.setProfilePicture(profilePicture);

        Profile createdProfile = profileService.createProfile(userEmail, 1L, profileDto);

        assertThat(createdProfile.getProfileNo()).isEqualTo(1);
        assertThat(createdProfile.getProfileName()).isEqualTo(profileName);
        log.info("Created Profile : {}", createdProfile);
    }

    @Test
    @Order(2)
    void 프로필_조회() throws NotFoundException {
        Profile findProfile = profileService.profileDetails(1L);
        assertThat(findProfile.getUser().getUserNo()).isEqualTo(1L);
        log.info("find Profile : {}", findProfile);
    }
}
