package com.faslow.flack.controller;

import com.faslow.flack.config.jwt.JwtProvider;
import com.faslow.flack.config.principal.UserPrincipal;
import com.faslow.flack.entity.UserWorkSpace;
import com.faslow.flack.entity.dto.user.UserLoginResponse;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.UserWorkRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(value = "Home")
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;
    private final UserWorkRepository userWorkRepository;

    @GetMapping("")
    @ApiOperation(value = "Home")
    public String home(@AuthenticationPrincipal UserPrincipal user) {
        System.out.println(user);
        if (user == null) {
            return "테스트";
        } else {
            return "로그인 성공 " + user.getUsername();
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<UserLoginResponse> loginJwt(@RequestBody Map<String, String> user) throws NotFoundException {
        User findUser = userRepository.findByUserEmail(user.get("userEmail"))
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        if (!passwordEncoder.matches(user.get("userPw"), findUser.getUserPw())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다");
        }
        String createdToken = jwtProvider.createToken(findUser.getUserEmail(), null);
        List<UserWorkSpace> allByUser = userWorkRepository.findAllByUser(findUser);
        List<WorkSpace> workSpaceList = new ArrayList<>();
        if (!allByUser.isEmpty()) {
            allByUser.forEach(a -> workSpaceList.add(a.getWorkSpace()));
        }
        return ResponseEntity.ok(new UserLoginResponse(createdToken, workSpaceList));
    }

//    @PostMapping("loginPost")
//    @ApiOperation(value = "loginPost")
//    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) throws NotFoundException {
//        User loginUser = userService.login(userLoginRequest.getUserEmail(), userLoginRequest.getUserPw());
//
//        List<UserWorkSpace> allByUser = userWorkRepository.findAllByUser(loginUser);
//        List<WorkSpace> workList = new ArrayList<>();
//        if (!allByUser.isEmpty()) {
//            allByUser.forEach(a -> workList.add(a.getWorkSpace()));
//        }
//        return ResponseEntity.ok(new UserLoginResponse(loginUser.getUserEmail(), workList));
//    }
}
