package com.faslow.flack.controller;

import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.user.UserUpdateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("user/{userId}")
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest) throws NotFoundException {
        User updateUser = userService.update(userId, userUpdateRequest);
        return ResponseEntity.ok(new UserDto(updateUser));
    }

}
