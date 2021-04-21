package com.faslow.flack.service;

import com.faslow.flack.entity.UserWorkSpace;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.UserRepository;
import com.faslow.flack.repository.UserWorkRepository;
import com.faslow.flack.repository.WorkSpaceRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserWorkSpaceService {

    private final UserWorkRepository userWorkRepository;
    private final WorkSpaceRepository workSpaceRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserWorkSpace create(User user, WorkSpace workSpace) {
        return userWorkRepository.save(new UserWorkSpace(user, workSpace));
    }

    @Transactional(readOnly = true)
    public WorkSpace joinWorkSpace(String userEmail, Long workspaceNo) throws NotFoundException {
        User findUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Not found User"));
        WorkSpace findWorkSpace = workSpaceRepository.findById(workspaceNo)
                .orElseThrow(() -> new NotFoundException("Not Found WorkSpace"));
        return userWorkRepository.save(new UserWorkSpace(findUser, findWorkSpace)).getWorkSpace();
    }


}
