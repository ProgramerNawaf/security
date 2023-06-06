package com.example.springsecurityy.Controller;

import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Repository.AuthRepository;
import com.example.springsecurityy.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    @GetMapping("/get")
    public List<MyUser> getAll(){
        return authService.get();
    }

    @PostMapping("/register")
    public ResponseEntity myUser(@RequestBody MyUser user){
        authService.register(user);
        return ResponseEntity.status(200).body("registered User!");
    }

    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("welcome admin!");
    }
    @PostMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("welcome user!");
    }
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login user!");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout user!");
    }





}
