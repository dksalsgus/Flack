package com.faslow.flack.service;

import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.dto.profile.ProfileUpdateRequest;
import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.repository.ProfileRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Autowired
    ProfileRepository profileRepository;

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

    @Test
    @Order(3)
    void 프로필_수정() throws NotFoundException {
        ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest();

        profileUpdateRequest.setProfileName(profileName);
        profileUpdateRequest.setProfileState(profileState);
        profileUpdateRequest.setProfilePicture(profilePicture);

        Profile updateProfile = profileService.updateProfile(1L, profileUpdateRequest);
        assertThat(updateProfile.getProfileNo()).isEqualTo(1L);
        assertThat(updateProfile.getProfileState()).isEqualTo(profileUpdateRequest.getProfileState());
        assertThat(updateProfile.getProfilePicture()).isEqualTo(profileUpdateRequest.getProfilePicture());

        log.info("update Profile : {}", updateProfile);
    }

    @Test
    @Order(4)
    void 프로필_삭제() throws NotFoundException {
        Optional<Profile> deleteProfile = profileRepository.findById(1L);
        profileService.deleteProfile(deleteProfile.get().getProfileNo());

        assertThat(deleteProfile.get().getProfileNo()).isEqualTo(1L);
        log.info("Deleted WorkSpace : {}", deleteProfile);
    }

}
