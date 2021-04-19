package com.faslow.flack.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeCotroller {

    @GetMapping("")
    public String home(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "테스트";
        } else {
            return "로그인 성공 " + user.getUsername();
        }
    }
}
