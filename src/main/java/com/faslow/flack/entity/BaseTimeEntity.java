package com.faslow.flack.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseTimeEntity {

    // 생성날짜
    @CreatedDate
    private LocalDateTime createAt;

    // 수정날짜
    @LastModifiedDate
    private LocalDateTime updateAt;

}


