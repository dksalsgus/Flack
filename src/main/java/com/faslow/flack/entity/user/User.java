package com.faslow.flack.entity.user;

import com.faslow.flack.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
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

    @Builder
    public User(String userEmail, String userPw, String userPhone) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
    }

}
