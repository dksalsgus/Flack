package com.faslow.flack.entity.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdateRequest {

    @ApiModelProperty(value = "비밀번호")
    private String userPw;

    @ApiModelProperty(value = "휴대폰번호")
    private String userPhone;

}


