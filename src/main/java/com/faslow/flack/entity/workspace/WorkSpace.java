package com.faslow.flack.entity.workspace;

import com.faslow.flack.entity.BaseTimeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@ApiModel
public class WorkSpace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "그룹 번호")
    private Long workspaceNo;

    @ApiModelProperty(required = true, value = "그룹 이름")
    private String workspaceName;

    @ApiModelProperty(value = "채널 번호")
    private int channelNo;

    @ApiModelProperty(value = "그룹 권한")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMBER'")
    private enWorkSpaceRole workspaceRole;

    public WorkSpace(String workspaceName, int channelNo, enWorkSpaceRole workspaceRole) {
        this.workspaceName = workspaceName;
        this.channelNo = channelNo;
        this.workspaceRole = workspaceRole;
    }

    public enum enWorkSpaceRole {
        OWNER,
        MEMBER
    }
}
