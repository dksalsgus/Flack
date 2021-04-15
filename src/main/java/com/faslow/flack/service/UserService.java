package com.faslow.flack.service;

import com.faslow.flack.entity.dto.user.UserDetailResponse;
import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.user.UserUpdateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public User join(UserDto userDto) {
        User user = userDto.toEntity();
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        userRepository.save(user);
        return new User(user.getUserEmail(), user.getUserPw(), user.getUserPhone());
    }

    // 회원정보 조회
    @Transactional
    public UserDetailResponse userInfo(Long userNo){
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("Not Found User"));
        return new UserDetailResponse(user);
    }

    // 회원정보 수정
    @Transactional
    public User update(Long userNo, UserUpdateRequest userUpdateRequest) throws NotFoundException {
        return userRepository.findById(userNo).map(user -> {
            user.update(userUpdateRequest.getUserPw(), userUpdateRequest.getUserPhone());
            return user;
        }).orElseThrow(() -> new NotFoundException("Not Found User"));
    }

}
