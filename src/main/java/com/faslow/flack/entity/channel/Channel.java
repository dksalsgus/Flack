package com.faslow.flack.entity.channel;

import com.faslow.flack.entity.BaseTimeEntity;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class Channel extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelNo;

    @NotNull
    private String channelName;

    private String channelInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspaceNo")
    private WorkSpace workSpace;
}
