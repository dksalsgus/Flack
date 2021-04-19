package com.faslow.flack.service;

import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.WorkSpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkSpaceServiceTest {

    @Autowired
    WorkSpaceService workSpaceService;

    @Autowired
    WorkSpaceRepository workSpaceRepository;

    private String workspaceName;
    private Long workspaceNo;


    @BeforeAll
    void setUp() {
        workspaceNo = 1L;
        workspaceName = "faslow";

    }

    @Test
    @Order(1)
    void 워크스페이스_생성() {
        WorkSpaceCreateRequest workspace = new WorkSpaceCreateRequest(this.workspaceName, 0);
        WorkSpace savedWorkSpace = workSpaceService.createWorkSpace(workspace);
        assertThat(savedWorkSpace.getWorkspaceName()).isEqualTo(workspaceName);
        log.info("Created workSpace Name : {}", savedWorkSpace.getWorkspaceName());
    }

    @Test
    @Order(2)
    void 워크스페이스_리스트() {
        List<WorkSpace> workSpaceList = workSpaceService.listWorkSpace();
        assertThat(workSpaceList).isNotNull();
        for (WorkSpace i : workSpaceList) {
            log.info("WorkSpace List : {}", i.getWorkspaceName());
        }
    }

    @Test
    @Order(3)
    void 워크스페이스_삭제(){
        WorkSpace workSpace = workSpaceService.deleteWorkSpace(workspaceNo);
        assertThat(workSpace).isNull();
        log.info("Deleted WorkSpace : ", workSpace);
    }

}
