package com.faslow.flack.entity.dto.user;

import com.faslow.flack.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @ApiModelProperty(value = "이메일")
    private String userEmail;

    @ApiModelProperty(value = "비밀번호")
    private String userPw;

    @ApiModelProperty(value = "휴대폰번호")
    private String userPhone;

    public UserDto(User user) {
        copyProperties(user, this);
    }

    @Builder
    public UserDto(String userEmail, String userPw, String userPhone) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
    }

    // toEntity() 메서드를 통해 dto에서 필요한 부분을 이용하여 entity로 변환
    public User toEntity() {
        return User.builder()
                .userEmail(userEmail)
                .userPw(userPw)
                .userPhone(userPhone)
                .build();
    }

}
