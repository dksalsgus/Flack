package com.faslow.flack.entity.dto.user;

import com.faslow.flack.entity.workspace.WorkSpace;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@ApiModel(value = "로그인 응답")
public class UserLoginResponse {

    @ApiModelProperty(value = "토큰")
    private String jwtToken;

    @ApiModelProperty(value = "워크스페이스 리스트")
    private List<WorkSpace> workSpaceList;

}
