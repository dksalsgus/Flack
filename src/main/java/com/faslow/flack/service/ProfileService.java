package com.faslow.flack.service;

import com.faslow.flack.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private ProfileRepository profileRepository;
}
