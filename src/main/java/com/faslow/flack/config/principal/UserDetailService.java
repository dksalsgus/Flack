package com.faslow.flack.config.principal;

import com.faslow.flack.entity.user.User;
import com.faslow.flack.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User findUser = null;
        try {
            findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new NotFoundException("Not Found User"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return new UserPrincipal(findUser);
    }
}
