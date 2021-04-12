package com.faslow.flack.controller;

import com.faslow.flack.dto.UserDto;
import com.faslow.flack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public String createUser(UserDto userDto){
        userService.join(userDto);
        return "redirect:/";
    }

}
