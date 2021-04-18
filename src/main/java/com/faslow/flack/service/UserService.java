package com.faslow.flack.service;

import com.faslow.flack.entity.dto.user.UserDetailResponse;
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

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public User join(UserDto userDto) {
        User user = userDto.toEntity();
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        return userRepository.save(new User(user.getUserEmail(), user.getUserPw(), user.getUserPhone()));
    }

    // 회원정보 조회
    public User userInfo(Long userNo) throws NotFoundException {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return user;
    }

    // 회원정보 수정
    @Transactional
    public User update(Long userNo, UserUpdateRequest userUpdateRequest) throws NotFoundException {
        return userRepository.findById(userNo).map(user -> {
            user.update(userUpdateRequest.getUserPw(), userUpdateRequest.getUserPhone());
            return user;
        }).orElseThrow(() -> new NotFoundException("Not Found User"));
    }

    // 회원탈퇴
    @Transactional
    public void delete(Long userNo) throws NotFoundException{
        userRepository.deleteById(userNo);
    }

}
