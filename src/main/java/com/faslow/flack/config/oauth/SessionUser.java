package com.faslow.flack.config.oauth;

import com.faslow.flack.entity.user.User;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private String email;

    public SessionUser(User user) {
        this.email = user.getUserEmail();
    }
}
