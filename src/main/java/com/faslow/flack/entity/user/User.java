package com.faslow.flack.entity.user;

import com.faslow.flack.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
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
    public User(Long userNo, String userEmail, String userPw, String userPhone){
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
    }

}
