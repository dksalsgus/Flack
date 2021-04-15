package com.faslow.flack.service;

import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.profile.Profile;
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

    private String profileName;
    private String profileState;
    private String profilePicture;

    @BeforeAll
    void setUp() {
        profileName = "profileName";
        profileState = "profileState";
        profilePicture = "profilePicturePath";
    }

    @Test
    @Order(1)
    void 프로필_생성() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileName(profileName);
        profileDto.setProfileState(profileState);
        profileDto.setProfilePicture(profilePicture);
        Profile createdProfile = profileService.createProfile(profileDto);

        assertThat(createdProfile.getProfileNo()).isEqualTo(1);
        assertThat(createdProfile.getProfileName()).isEqualTo(profileName);
        log.info("Created Profile : {}", createdProfile);
    }
}
