package com.faslow.flack.controller;

import com.faslow.flack.config.principal.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeCotroller {

    @GetMapping("")
    public String home(@AuthenticationPrincipal UserPrincipal user) {
        if (user == null) {
            return "테스트";
        } else {
            return "로그인 성공 " + user.getUsername();
        }
    }
}
