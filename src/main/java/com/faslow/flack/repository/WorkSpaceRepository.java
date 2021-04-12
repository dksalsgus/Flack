package com.faslow.flack.repository;

import com.faslow.flack.entity.workspace.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSpaceRepository extends JpaRepository<WorkSpace,Long> {
}
