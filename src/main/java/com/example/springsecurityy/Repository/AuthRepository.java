package com.example.springsecurityy.Repository;

import com.example.springsecurityy.Model.Blog;
import com.example.springsecurityy.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {

        MyUser findMyUserByUsername(String username);
        MyUser findMyUsersById(Integer userId);

}
