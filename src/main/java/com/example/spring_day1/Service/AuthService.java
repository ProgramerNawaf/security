package com.example.spring_day1.Service;

import com.example.spring_day1.Model.MyUser;
import com.example.spring_day1.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    public List<MyUser> getUsers(){
        return authRepository.findAll();
    }

    public void registerUser(MyUser myUser){
        String hash = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("USER");
        //myUser.setPassword(String.valueOf(myUser.getPassword().hashCode()));
        authRepository.save(myUser);
    }

//    public void updateUser(String username, MyUser myUser){
//        MyUser myUser1 = authRepository.findMyUserByUsername(username);
//
////        if (myUser1 == null){
////            throw new ApiException("ID Not Found");
////        }
//
//        myUser1.setUsername(myUser.getUsername());
//        myUser1.setPassword(myUser.getPassword());
//
//        authRepository.save(myUser1);
//    }
}
