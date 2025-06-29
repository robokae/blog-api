package com.robokae.blog.controller;

import com.robokae.blog.model.Post;
import com.robokae.blog.model.Response;
import com.robokae.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<Response> fetchPosts(@RequestParam(required = false) String page,
                                               @RequestParam(required = false) String sortBy) {

        List<Post> posts = postService.fetchAllPosts();
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .data(posts).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{title}")
    public ResponseEntity<Response> fetchPost(@PathVariable("title") String title) {
        Post post = postService.fetchPostByTitle(title);
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .data(post).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post")
    public ResponseEntity<Response> createPost(@RequestBody Post post) {
        postService.createPost(post);
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .message("Successfully created post").build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/post")
    public ResponseEntity<Response> updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        Response response = Response.builder()
                .status(HttpStatus.CREATED.value())
                .message("Successfully updated post").build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/post/{title}")
    public ResponseEntity<Response> deletePost(@PathVariable("title") String title) {
        postService.deletePost(title);
        Response response = Response.builder()
                .status(HttpStatus.OK.value())
                .message("Successfully deleted post").build();
        return ResponseEntity.ok(response);
    }
}