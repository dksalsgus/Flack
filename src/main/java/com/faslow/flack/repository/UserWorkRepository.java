package com.faslow.flack.repository;

import com.faslow.flack.entity.UserWorkSpace;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWorkRepository extends JpaRepository<UserWorkSpace, Long> {


    List<UserWorkSpace> findAllByWorkSpace(WorkSpace workSpace);

    List<UserWorkSpace> findAllByUser(User user);

}
