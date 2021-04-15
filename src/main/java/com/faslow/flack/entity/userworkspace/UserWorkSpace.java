package com.faslow.flack.entity.userworkspace;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@IdClass(UserWorkSpacePk.class)
public class UserWorkSpace {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workSpaceNo")
    @Id
    private WorkSpace workSpaceNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    @Id
    private User userNo;

    private String workSpaceRole;

}
