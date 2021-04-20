package com.faslow.flack.entity.dto.profile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "프로필 편집")
@AllArgsConstructor
public class ProfileUpdateRequest {

    @ApiModelProperty(value = "프로필 이름")
    private String profileName;

    @ApiModelProperty(value = "프로필 상태메세지")
    private String profileState;

    @ApiModelProperty(value = "프로필 사진 경로")
    private String profilePicture;

}
