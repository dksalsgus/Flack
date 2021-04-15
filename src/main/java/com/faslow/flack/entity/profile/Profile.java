package com.faslow.flack.entity.profile;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileNo;

    @NotNull
    private String profileName;

    private String profileState;

    private String profilePicture;

    public Profile(String profileName, String profileState, String profilePicture) {
        this.profileName = profileName;
        this.profileState = profileState;
        this.profilePicture = profilePicture;
    }
}
