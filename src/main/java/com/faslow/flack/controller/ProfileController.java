package com.faslow.flack.controller;

import com.faslow.flack.config.principal.UserPrincipal;
import com.faslow.flack.entity.dto.profile.ProfileDetailResponse;
import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.repository.WorkSpaceRepository;
import com.faslow.flack.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Api(value = "Profile API")
public class ProfileController {

    private final ProfileService profileService;

    private final WorkSpaceRepository workSpaceRepository;


    @ApiOperation(value = "프로필 등록")
    @PostMapping("{workspaceNo}}/profile")
    public ResponseEntity<ProfileDto>
    registerProfile(@AuthenticationPrincipal UserPrincipal userPrincipal,
                    @PathVariable Long workspaceNo,
                    String profileName,
                    String profileState,
                    @RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {
        try {
            String origFilename = profilePicture.getOriginalFilename();
            String filename = "_" + origFilename;
            // 실행되는 위치의 'files' 폴더에 파일이 저장
            String savePath = System.getProperty("user.dir") + "\\flackProfileImg";

            // 파일이 저장되는 폴더가 없으면 폴더를 생성
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir(); // 폴더 생성
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            profilePicture.transferTo(new File(filePath));
            ProfileDto profileDto = new ProfileDto();
            profileDto.setProfileName(profileName);
            profileDto.setProfileState(profileState);
            profileDto.setProfilePicture(filePath);
            Profile savedProfile = profileService.createProfile(userPrincipal.getUsername(), workspaceNo, profileDto);
            return ResponseEntity.ok(new ProfileDto(savedProfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "프로필 조회")
    @GetMapping("profile/{profileNo}")
    public ResponseEntity<ProfileDetailResponse> profileDetails(@PathVariable Long profileNo) throws NotFoundException {
        Profile profile = profileService.profileDetails(profileNo);
        return ResponseEntity.ok(new ProfileDetailResponse(profile.getProfileName(), profile.getProfileState(), profile.getProfilePicture()));
    }
}
