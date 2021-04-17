package com.faslow.flack.repository;

import com.faslow.flack.entity.UserWorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkRepository extends JpaRepository<UserWorkSpace, Long> {
}
