package com.faslow.flack.service;

import com.faslow.flack.entity.dto.profile.ProfileDto;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.repository.ProfileRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile createProfile(ProfileDto profileDto) {
        return profileRepository.save(new Profile(profileDto.getProfileName(), profileDto.getProfileState(), profileDto.getProfilePicture(), profileDto.getUserNo()));
    }

    @Transactional
    public Profile profileDetails(Long profileNo) throws NotFoundException {
        return profileRepository.findById(profileNo).orElseThrow(() -> new NotFoundException("Not Found Profile"));
    }

}
