package com.example.springsecurityy.Repository;

import com.example.springsecurityy.Model.Blog;
import com.example.springsecurityy.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    List<Blog> findBlogsByUser(MyUser myUser);
    Blog findBlogById(Integer id);
    Blog findBlogByTitle(String title);
}
