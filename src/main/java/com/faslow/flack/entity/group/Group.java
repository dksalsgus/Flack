package com.faslow.flack.entity.group;

import com.faslow.flack.entity.BaseTimeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@ApiModel
public class Group extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "그룹 번호")
    private Integer groupNo;

    @ApiModelProperty(required = true, value = "그룹 이름")
    private Integer groupName;

    @ApiModelProperty(value = "채널 번호")
    private Integer channelNo;

    @ApiModelProperty(value = "그룹 권한")
    @Enumerated(EnumType.STRING)
    private String groupRole;

    public enum enGroupRole {
        OWNER,
        MEMBER
    }
}
