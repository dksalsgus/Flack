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

    @ApiModelProperty(value = "이메일")
    private String userEmail;

    @ApiModelProperty(value = "비밀번호")
    private String userPw;

    @ApiModelProperty(value = "휴대폰번호")
    private String userPhone;

    @ApiModelProperty(value = "생성날짜")
    private LocalDateTime createAt;

    @ApiModelProperty(value = "수정날짜")
    private LocalDateTime updateAt;

    public UserDetailResponse(User user) {
        copyProperties(user, this);
    }

    @Builder
    public UserDetailResponse(String userEmail, String userPw, String userPhone, LocalDateTime createAt, LocalDateTime updateAt) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
        this.createAt = createAt;
        this.updateAt = updateAt;

    }

}
