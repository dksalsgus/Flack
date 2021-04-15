package com.faslow.flack.entity.userworkspace;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserWorkSpacePk implements Serializable {

    private WorkSpace workSpaceNo;

    private User userNo;
}
