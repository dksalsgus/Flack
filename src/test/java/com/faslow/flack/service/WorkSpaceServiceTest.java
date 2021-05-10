package com.faslow.flack.service;

import com.faslow.flack.entity.UserWorkSpace;
import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.ProfileRepository;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.UserWorkRepository;
import com.faslow.flack.repository.WorkSpaceRepository;
import javassist.NotFoundException;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWorkRepository userWorkRepository;

    @Autowired
    ProfileRepository profileRepository;

    private String workspaceName;
    private String profileName;
    private Long workspaceNo;
    private Long profileNo;


    @BeforeAll
    void setUp() {
        profileNo = 1L;
        profileName = "flack";
        workspaceNo = 1L;
        workspaceName = "faslow";
        userRepository.save(new User("userEmail", "userPw", "010-1234-1234"));
    }

    @Test
    @Order(1)
    void 워크스페이스_생성() throws NotFoundException {
        WorkSpaceCreateRequest workspace = new WorkSpaceCreateRequest(this.workspaceName);
        WorkSpace savedWorkSpace = workSpaceService.createWorkSpace("userEmail", workspace);
        assertThat(savedWorkSpace.getWorkspaceName()).isEqualTo(workspaceName);
        log.info("Created workSpace Name : {}", savedWorkSpace);
        List<UserWorkSpace> findWorkSpaces = userWorkRepository.findAllByWorkSpace(savedWorkSpace);
        log.info("find workspace : {}", findWorkSpaces);
    }

    @Test
    @Order(2)
    void 워크스페이스_리스트() {
        List<WorkSpace> workSpaceList = workSpaceService.listWorkSpace();
        assertThat(workSpaceList).isNotNull();
        log.info("WorkSpaceList : {}", workSpaceList);
    }

    @Test
    @Order(3)
    void 워크스페이스_삭제() throws NotFoundException {
        Optional<WorkSpace> deleteWorkSpace = workSpaceRepository.findById(workspaceNo);
        workSpaceService.deleteWorkSpace(workspaceNo);

        assertThat(deleteWorkSpace.get().getWorkspaceName()).isEqualTo("faslow");
        log.info("Deleted WorkSpace {}: ", deleteWorkSpace);
    }

}
