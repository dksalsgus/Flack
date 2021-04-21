package com.faslow.flack.entity;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserWorkSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userWorkNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userNo")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workspaceNo")
    private WorkSpace workSpace;

    public UserWorkSpace(User user, WorkSpace workSpace) {
        this.user = user;
        this.workSpace = workSpace;
    }
}
