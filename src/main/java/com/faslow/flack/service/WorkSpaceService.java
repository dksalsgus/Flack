package com.faslow.flack.service;

import com.faslow.flack.entity.dto.workspace.WorkSpaceCreateRequest;
import com.faslow.flack.entity.profile.Profile;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.ProfileRepository;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.WorkSpaceRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkSpaceService {

    private final WorkSpaceRepository workSpaceRepository;

    private final UserWorkSpaceService userWorkSpaceService;

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;


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
    public void deleteWorkSpace(Long workspaceNo, Long profileNo) throws NotFoundException {
        try {
            workSpaceRepository.deleteById(workspaceNo);
            profileRepository.deleteById(profileNo);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

}
