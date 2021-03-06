package com.faslow.flack.entity.profile;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.entity.workspace.WorkSpace;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileNo;

    @NotNull
    private String profileName;

    private String profileState;

    private String profilePicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    private User user;

    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'MEMBER'")
    private enRole workspaceRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspaceNo")
    private WorkSpace workSpace;

    @Builder
    public Profile(String profileName, String profileState, String profilePicture, User userNo, enRole workspaceRole, WorkSpace workSpace) {
        this.profileName = profileName;
        this.profileState = profileState;
        this.profilePicture = profilePicture;
        this.user = userNo;
        this.workspaceRole = workspaceRole;
        this.workSpace = workSpace;
    }

    public Profile update(String profileName, String profileState, String profilePicture) {
        this.profileName = profileName;
        this.profileState = profileState;
        this.profilePicture = profilePicture;
        return this;
    }

    public enum enRole {
        OWNER,
        MEMBER
    }


}
