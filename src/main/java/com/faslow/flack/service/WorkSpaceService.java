package com.faslow.flack.service;

import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.WorkSpaceRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkSpaceService {

    private final WorkSpaceRepository workSpaceRepository;

    private final UserWorkSpaceService userWorkSpaceService;

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public WorkSpace createWorkSpace(String userEmail, WorkSpaceCreateRequest workSpaceCreateRequest) throws NotFoundException {
        User findUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        WorkSpace saveWorkSpace = workSpaceRepository.save(
                new WorkSpace(workSpaceCreateRequest.getWorkspaceName()));
        userWorkSpaceService.create(findUser, saveWorkSpace);
        return saveWorkSpace;
    }

    @Transactional(readOnly = true)
    public List<WorkSpace> listWorkSpace() {
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        return workSpaceList;
    }

    @Transactional(readOnly = true)
    public WorkSpace deleteWorkSpace(Long workspaceNo) {
        WorkSpace workSpace = workSpaceRepository.findById(workspaceNo)
                .orElseThrow(() -> new IllegalArgumentException("Not Found WorkSpace" + workspaceNo));
        workSpaceRepository.delete(workSpace);
        return workSpace;
    }

}
