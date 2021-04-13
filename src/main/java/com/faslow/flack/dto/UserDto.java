package com.faslow.flack.dto;

import com.faslow.flack.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String userEmail;
    private String userPw;
    private String userPhone;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public UserDto(User user) {
        copyProperties(user, this);
    }

    @Builder
    public UserDto(String userEmail, String userPw, String userPhone) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
    }

    // toEntity() 메서드를 통해 dto에서 필요한 부분을 이용하여 entity로 변환
    public User toEntity() {
        return User.builder()
                .userEmail(userEmail)
                .userPw(new BCryptPasswordEncoder().encode(userPw))
                .userPhone(userPhone)
                .build();
    }

}
