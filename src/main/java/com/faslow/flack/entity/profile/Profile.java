package com.faslow.flack.entity.profile;

import com.faslow.flack.entity.user.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public Profile(String profileName, String profileState, String profilePicture, User userNo) {
        this.profileName = profileName;
        this.profileState = profileState;
        this.profilePicture = profilePicture;
        this.user = userNo;
    }
}
