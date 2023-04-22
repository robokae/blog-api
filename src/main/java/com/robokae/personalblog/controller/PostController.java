package com.robokae.personalblog.controller;

import com.robokae.common.model.Post;
import com.robokae.personalblog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/personalblog")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/{title}")
    public Post getPostByTitle(@PathVariable String title) {
        return postService.getPostByTitle(title);
    }

    @PostMapping("/post")
    public void createPost(@RequestBody Post post) {
        log.info("Saving post: {}", post);
        postService.createPost(post);
    }

    @PutMapping()
    public void updatePost(HttpRequest request) {

    }

    @DeleteMapping
    public void deletePost(HttpRequest request) {

    }
}
