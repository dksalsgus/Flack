package com.faslow.flack.entity.workspace;

import com.faslow.flack.entity.BaseTimeEntity;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@ApiModel
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkSpace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceNo;

    private String workspaceName;

    private int channelNo;

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
