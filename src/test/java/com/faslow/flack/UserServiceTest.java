package com.faslow.flack;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void join(){
        userRepository.save(User.builder()
                .userEmail("email test")
                .userPw("pw test")
                .userPhone("010-0000-0000")
                .build());

        }
}
