package com.faslow.flack.service;

import com.faslow.flack.entity.UserWorkSpace;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.faslow.flack.repository.UserWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserWorkSpaceService {

    private final UserWorkRepository userWorkRepository;

    @Transactional
    public UserWorkSpace create(User user, WorkSpace workSpace) {
        return userWorkRepository.save(new UserWorkSpace(user, workSpace));
    }

}
