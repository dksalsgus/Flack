package com.faslow.flack.service;

import com.faslow.flack.entity.dto.user.UserDto;
import com.faslow.flack.entity.dto.user.UserUpdateRequest;
import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User join(UserDto userDto) {
        User user = userDto.toEntity();
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        userRepository.save(user);
        return new User(user.getUserEmail(), user.getUserPw(), user.getUserPhone());
    }

    @Transactional
    public User update(Long userId, UserUpdateRequest userUpdateRequest) throws NotFoundException {
        return userRepository.findById(userId).map(user -> {
            user.update(userUpdateRequest.getUserPw(), userUpdateRequest.getUserPhone());
            return user;
        }).orElseThrow(() -> new NotFoundException("Not Found User"));
    }
}
