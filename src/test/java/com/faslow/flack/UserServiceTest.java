package com.faslow.flack;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DataJpaTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void join(){

        User user = User.builder()
                .userEmail("email")
                .userPw("userpw")
                .userPhone("010-0000-0000")
                .build();

        userRepository.save(user);

        }
}
