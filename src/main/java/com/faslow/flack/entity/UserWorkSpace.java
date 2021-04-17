package com.faslow.flack.entity;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
public class UserWorkSpace {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    private final User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspaceNo")
    private final WorkSpace workSpace;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userWorkNo;

    public UserWorkSpace(User user, WorkSpace workSpace) {
        this.user = user;
        this.workSpace = workSpace;
    }
}
