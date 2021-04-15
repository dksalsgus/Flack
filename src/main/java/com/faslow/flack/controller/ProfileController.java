package com.faslow.flack.controller;

import com.faslow.flack.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private ProfileService profileService;
}
