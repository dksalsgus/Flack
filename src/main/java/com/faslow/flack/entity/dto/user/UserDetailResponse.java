package com.faslow.flack.entity.dto.user;

import com.faslow.flack.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@NoArgsConstructor
public class UserDetailResponse{

    @ApiModelProperty(value = "회원번호")
    private Long userNo;

    @ApiModelProperty(value = "이메일")
    private String userEmail;

    @ApiModelProperty(value = "비밀번호")
    private String userPw;

    @ApiModelProperty(value = "휴대폰번호")
    private String userPhone;

    public UserDetailResponse(User user) {
        copyProperties(user, this);
    }

    @Builder
    public UserDetailResponse(Long userNo, String userEmail, String userPw, String userPhone, LocalDateTime createAt, LocalDateTime updateAt) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
        this.createAt = createAt;
        this.updateAt = updateAt;

    }

}