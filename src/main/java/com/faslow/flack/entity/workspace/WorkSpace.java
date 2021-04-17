package com.faslow.flack.entity.workspace;

import com.faslow.flack.entity.BaseTimeEntity;
import com.faslow.flack.entity.channel.Channel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@ToString
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channelNo")
    private Channel channel;

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
