package com.faslow.flack.entity.dto.profile;

import com.faslow.flack.entity.profile.Profile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "프로필")
public class ProfileDto {

    @ApiModelProperty(value = "프로필 이름")
    private String profileName;

    @ApiModelProperty(value = "프로필 상태메세지")
    private String profileState;

    @ApiModelProperty(value = "프로필 사진 경로")
    private String profilePicture;

    @ApiModelProperty(value = "권한")
    private Profile.enRole workspaceRole;


    public ProfileDto(Profile profile) {
        BeanUtils.copyProperties(profile, this);
    }
}
