package com.faslow.flack.entity.user;

import com.faslow.flack.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class User extends BaseTimeEntity {

    // 회원번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    // 이메일주소
    @NotNull
    @Column(unique = true)
    private String userEmail;

    // 비밀번호
    @NotNull
    private String userPw;

    // 휴대폰번호
    private String userPhone;

}
