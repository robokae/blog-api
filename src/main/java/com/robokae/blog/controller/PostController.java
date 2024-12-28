package com.robokae.blog.controller;

import com.robokae.blog.service.PostService;
import com.robokae.blog.model.Post;
import com.robokae.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Response> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .data(posts)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{title}")
    public ResponseEntity<Response> getPost(@PathVariable("title") String title) {
        Post post = postService.getPost(title);
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .data(post)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post")
    public ResponseEntity<Response> createPost(@RequestBody Post post) {
        postService.savePost(post);
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .message("Successfully created post")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/post/{title}")
    public ResponseEntity<Response> updatePost(@PathVariable("title") String title, @RequestBody Post post) {
        postService.updatePost(title, post);
        Response response = Response.builder()
                .status(HttpStatus.CREATED.value())
                .message("Successfully updated post")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/post/{title}")
    public ResponseEntity<Response> deletePost(@PathVariable("title") String title) {
        postService.deletePost(title);
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .message("Successfully deleted post")
                .build();
        return ResponseEntity.ok(response);
    }
}