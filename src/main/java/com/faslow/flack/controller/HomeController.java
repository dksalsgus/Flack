package com.faslow.flack.controller;

import com.faslow.flack.config.principal.UserPrincipal;
import com.faslow.flack.repository.UserWorkRepository;
import com.faslow.flack.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Home")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    private final UserWorkRepository userWorkRepository;

    @GetMapping("")
    @ApiOperation(value = "Home")
    public String home(@AuthenticationPrincipal UserPrincipal user) {
        if (user == null) {
            return "테스트";
        } else {
            return "로그인 성공 " + user.getUsername();
        }
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
