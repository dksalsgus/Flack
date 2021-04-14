package com.faslow.flack.controller;

import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateResponse;
import com.faslow.flack.entity.dto.workspace.WorkSpaceDto;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.service.WorkSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "워크스페이스 api")
public class WorkSpaceController {

    private final WorkSpaceService workSpaceService;

    @ApiOperation(value = "워크 스페이스 생성")
    @PostMapping("workspace")
    public ResponseEntity<WorkSpaceCreateResponse> createWorkSpace(@RequestBody WorkSpaceCreateRequest workSpaceCreateRequest) {
        WorkSpace saveWorkSpace = workSpaceService.createWorkSpace(workSpaceCreateRequest);
        return ResponseEntity.ok(new WorkSpaceCreateResponse(saveWorkSpace));
    }

    @ApiOperation(value = "워크스페이스 리스트")
    @GetMapping("workspace")
    public ResponseEntity<List<WorkSpaceDto>> workSpaceList() {
        List<WorkSpace> workSpaceList = workSpaceService.listWorkSpace();
        List<WorkSpaceDto> dtoList = new ArrayList<>();
        for (WorkSpace i : workSpaceList) {
            WorkSpaceDto dto = new WorkSpaceDto(i);
            dtoList.add(dto);
        }
        return ResponseEntity.ok(dtoList);
    }
}
