package com.faslow.flack.entity.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ApiModel
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "그룹 번호")
    private Integer groupNo;

    @ApiModelProperty(required = true,value = "그룹 이름")
    private Integer groupName;

    @ApiModelProperty(value = "채널 번호")
    private Integer channelNo;

    @ApiModelProperty(value = "그룹 권한")
    private String groupRole;

}
