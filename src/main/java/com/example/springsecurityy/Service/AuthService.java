package com.example.springsecurityy.Service;

import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<MyUser> get(){
        return authRepository.findAll();
    }
    public void register(MyUser usr){
        String hash = new BCryptPasswordEncoder().encode(usr.getPassword());
        usr.setPassword(hash);
//        usr.setRole("CUSTOMER");
        authRepository.save(usr);
    }
}
