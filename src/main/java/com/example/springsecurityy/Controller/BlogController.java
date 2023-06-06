package com.example.springsecurityy.Controller;

import com.example.springsecurityy.Model.Blog;
import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogs(myUser.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog){
        blogService.addBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body("Blog Added");
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog, @PathVariable Integer blogId){
        blogService.updateBlog(myUser.getId(),blog,blogId);
        return ResponseEntity.status(200).body("Blog Updated");
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId){
        blogService.deleteBlog(myUser.getId(),blogId);
        return ResponseEntity.status(200).body("Blog Deleted");
    }

    @GetMapping("/get-id/{blogId}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId){

        return ResponseEntity.status(200).body(blogService.getBlogById(myUser.getId(),blogId));
    }

    @GetMapping("/get-title/{title}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser, @PathVariable String title){

        return ResponseEntity.status(200).body(blogService.getBlogByTitle(myUser.getId(),title));
    }

}
