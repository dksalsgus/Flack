package com.faslow.flack.service;

import com.faslow.flack.entity.UserWorkSpace;
import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.UserWorkRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkSpaceServiceTest {

    @Autowired
    WorkSpaceService workSpaceService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWorkRepository userWorkRepository;

    private String workspaceName;
    private Long workspaceNo;


    @BeforeAll
    void setUp() {
        workspaceNo = 1L;
        workspaceName = "faslow";

        userRepository.save(new User("userEmail", "userPw", "010-1234-1234"));
    }

    @Test
    @Order(1)
    @Transactional
    void 워크스페이스_생성() throws NotFoundException {
        WorkSpaceCreateRequest workspace = new WorkSpaceCreateRequest(this.workspaceName, 0);
        WorkSpace savedWorkSpace = workSpaceService.createWorkSpace("userEmail", workspace);
        assertThat(savedWorkSpace.getWorkspaceName()).isEqualTo(workspaceName);
        log.info("Created workSpace Name : {}", savedWorkSpace.getWorkspaceName());
        List<UserWorkSpace> findWorkSpaces = userWorkRepository.findAllByWorkSpace(savedWorkSpace);
        log.info("find workspace : {}", findWorkSpaces);
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
    @Transactional
    void 워크스페이스_삭제() {
        userWorkRepository.deleteById(1L);
        WorkSpace workSpace = workSpaceService.deleteWorkSpace(workspaceNo);
        assertThat(workSpace).isNull();
        log.info("Deleted WorkSpace : ", workSpace);
    }

}
