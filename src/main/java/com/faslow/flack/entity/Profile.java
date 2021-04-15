package com.faslow.flack.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Profile {

    // 프로필번호
    private Long profileNo;

    // 프로필이름
    private String profileName;

    // 프로필상태
    private String profileState;

    // 프로필사진
    private String profilePicture;
}
