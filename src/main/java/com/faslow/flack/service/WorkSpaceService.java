package com.faslow.flack.service;

import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkSpaceService {

    private final WorkSpaceRepository workSpaceRepository;

    private final UserWorkSpaceService userWorkSpaceService;


    @Transactional
    public WorkSpace createWorkSpace(WorkSpaceCreateRequest workSpaceCreateRequest) {
        return workSpaceRepository.save(
                new WorkSpace(workSpaceCreateRequest.getWorkspaceName()));
    }

    public List<WorkSpace> listWorkSpace() {
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        return workSpaceList;
    }

    @Transactional
    public void deleteWorkSpace(Long workspaceNo){
        WorkSpace workSpace = workSpaceRepository.findById(workspaceNo)
                .orElseThrow(() -> new IllegalArgumentException("Not Found WorkSpace" + workspaceNo));
        workSpaceRepository.delete(workSpace);
    }

}
