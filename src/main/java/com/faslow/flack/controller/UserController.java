package com.faslow.flack.controller;

import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "User Api")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User saveUser = userService.join(userDto);
        return ResponseEntity.ok(new UserDto(saveUser));
    }
}
