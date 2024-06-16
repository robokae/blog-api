package com.robokae;

import java.util.Date;

import com.robokae.blog.repository.PostRepository;
import com.robokae.blog.model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeDatabase implements CommandLineRunner  {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) {
//        postRepository.deleteAll();
        Post post = Post.builder()
                .title("My First Post")
                .publishDate(new Date())
                .author("Alexander Hom")
                .body("This is my first post!")
                .build();
        postRepository.save(post);
    }
}
