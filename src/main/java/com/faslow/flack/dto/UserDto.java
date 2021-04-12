package com.faslow.flack.dto;

import com.faslow.flack.entity.user.User;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long userNo;
    private String userEmail;
    private String userPw;
    private String userPhone;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // toEntity() 메서드를 통해 dto에서 필요한 부분을 이용하여 entity로 변환
    public User toEntity(){
        return User.builder()
                .userNo(userNo)
                .userEmail(userEmail)
                .userPw(new BCryptPasswordEncoder().encode(userPw))
                .userPhone(userPhone)
                .build();
    }

    @Builder
    public UserDto(Long userNo, String userEmail, String userPw, String userPhone) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
    }

}
