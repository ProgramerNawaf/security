package com.example.springsecurityy.Service;

import com.example.springsecurityy.Model.Blog;
import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Repository.AuthRepository;
import com.example.springsecurityy.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getBlogs(Integer userId) {
        return blogRepository.findBlogsByUser(authRepository.findMyUsersById(userId));
    }

    public void addBlog(Integer userId, Blog blog) {
        blog.setUser(authRepository.findMyUsersById(userId));
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Blog blog, Integer blogId) {
        Blog oldBlog = blogRepository.findBlogById(blogId);

        if (oldBlog == null ){
//            throw new ApiException("Blog Not found");
            return;
        }
        MyUser user = authRepository.findMyUsersById(userId);
        if (oldBlog.getUser() != user){
//            throw new ApiException("Not Authorised");
            return;
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);

    }

    public void deleteBlog(Integer userId, Integer blogId) {
        Blog blog = blogRepository.findBlogById(blogId);

        if (blog == null ){
    //            throw new ApiException("Blog Not found");
            return;
        }

        MyUser user = authRepository.findMyUsersById(userId);
        if (blog.getUser() != user){
//            throw new ApiException("Not Authorised");
            return;
        }
        blogRepository.delete(blog);


    }

    public Blog getBlogById(Integer userId , Integer blogId){
        Blog blog = blogRepository.findBlogById(blogId);

        if (blog == null ){
            //            throw new ApiException("Blog Not found");
            return null;
        }

        MyUser user = authRepository.findMyUsersById(userId);
        if (blog.getUser() != user){
//            throw new ApiException("Not Authorised");
            return null;
        }
        return blog;
    }

    public Blog getBlogByTitle(Integer userId , String title){
        Blog blog = blogRepository.findBlogByTitle(title);

        if (blog == null ){
            //            throw new ApiException("Blog Not found");
            return null;
        }

        MyUser user = authRepository.findMyUsersById(userId);
        if (blog.getUser() != user){
//            throw new ApiException("Not Authorised");
            return null;
        }
        return blog;
    }
}
