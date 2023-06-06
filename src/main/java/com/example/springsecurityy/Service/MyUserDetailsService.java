package com.example.springsecurityy.Service;

import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Repository.AuthRepository;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {


    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user=authRepository.findMyUserByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }

        return user;
    }
}