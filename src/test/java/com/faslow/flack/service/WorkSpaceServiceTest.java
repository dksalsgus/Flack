package com.faslow.flack.service;

import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.workspace.WorkSpace;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkSpaceServiceTest {

    @Autowired
    WorkSpaceService workSpaceService;

    private String workspaceName;

    @BeforeAll
    void setUp() {
        workspaceName = "faslow";
    }

    @Order(1)
    @Test
    void 워크스페이스_생성() {
        WorkSpaceCreateRequest workspace = new WorkSpaceCreateRequest(this.workspaceName, 0, null, LocalDateTime.now(), LocalDateTime.now());
        WorkSpace savedWorkSpace = workSpaceService.createWorkSpace(workspace);
        assertThat(savedWorkSpace.getWorkspaceName()).isEqualTo(workspaceName);
        log.info("Created workSpace Name : {}", savedWorkSpace.getWorkspaceName());
    }
}
