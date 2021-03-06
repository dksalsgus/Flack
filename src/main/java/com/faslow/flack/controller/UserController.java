package com.faslow.flack.controller;

import com.faslow.flack.entity.dto.user.UserDetailResponse;
import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.user.UserUpdateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.service.ProfileService;
import com.faslow.flack.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "User Api")
public class UserController {

    private final UserService userService;

    private final ProfileService profileService;

    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/join")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        User saveUser = userService.join(userDto);
        return ResponseEntity.ok(new UserDto(saveUser));
    }

    // 회원정보 수정
    @PatchMapping("user/{userNo}")
    @ApiOperation(value = "회원정보 수정")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userNo, @RequestBody UserUpdateRequest userUpdateRequest) throws NotFoundException {
        User updateUser = userService.update(userNo, userUpdateRequest);
        return ResponseEntity.ok(new UserDto(updateUser));
    }

    // 회원정보 조회
    @GetMapping("user/{userNo}")
    @ApiOperation(value = "회원정보 수정")
    public ResponseEntity<UserDetailResponse> getUser(@PathVariable Long userNo) throws NotFoundException {
        User getUser = userService.userInfo(userNo);
        return ResponseEntity.ok(new UserDetailResponse(getUser));
    }

    // 회원탈퇴
    @DeleteMapping("user/{userNo}")
    @ApiOperation(value = "회원탈퇴")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userNo, @PathVariable Long profileNo) throws NotFoundException {
        userService.delete(userNo);
        profileService.deleteProfile(profileNo);
        return ResponseEntity.noContent().build();
    }

}
