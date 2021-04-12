package com.faslow.flack.controller;

import com.faslow.flack.service.GroupService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "그룹 컨트롤러")
public class GroupController {

    private final GroupService groupService;
}
