package com.faslow.flack.service;

import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.ProfileRepository;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.UserWorkRepository;
import com.faslow.flack.repository.WorkSpaceRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final WorkSpaceRepository workSpaceRepository;
    private final UserWorkRepository userWorkRepository;

    @Transactional(readOnly = true)
    public Profile createProfile(String userEmail, Long workspaceNo, ProfileDto profileDto) throws NotFoundException {
        User findUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        WorkSpace findWorkSpace = workSpaceRepository.findById(workspaceNo)
                .orElseThrow(() -> new NotFoundException("Not Found WorkSpace"));
        // 해당 워크스페이스에 등록된 유저가 한명이면(workspace생성자가 profile 생성 중이라는 뜻)
        if (userWorkRepository.findAllByWorkSpace(findWorkSpace).size() == 1) {
            profileDto.setWorkspaceRole(Profile.enRole.OWNER);
        }
        return profileRepository
                .save(new Profile(profileDto.getProfileName(), profileDto.getProfileState(), profileDto.getProfilePicture(), findUser, profileDto.getWorkspaceRole(), findWorkSpace));
    }

    @Transactional(readOnly = true)
    public Profile profileDetails(Long profileNo) throws NotFoundException {
        return profileRepository.findById(profileNo).orElseThrow(() -> new NotFoundException("Not Found Profile"));
    }

}
