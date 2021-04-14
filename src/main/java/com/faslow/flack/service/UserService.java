package com.faslow.flack.service;

import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public User join(UserDto userDto){
        User user = userDto.toEntity();
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        userRepository.save(user);
       return new User(user.getUserEmail(),user.getUserPw(),user.getUserPhone());
    }

    // 회원정보 조회
    public String getUserByEmail(){
        return null;
    }

}
