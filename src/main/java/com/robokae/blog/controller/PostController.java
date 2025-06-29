package com.robokae.blog.controller;

import com.robokae.blog.dto.PostRequest;
import com.robokae.blog.model.Post;
import com.robokae.blog.service.PostService;
import com.robokae.blog.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.robokae.blog.constant.PostConstants.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<Object> fetchPosts(@RequestParam(required = false) String page,
                                             @RequestParam(required = false) String sortBy) {
        List<Post> posts = postService.fetchAllPosts();
        return ResponseUtil.createResponse(HttpStatus.OK, posts);
    }

    @GetMapping("/post/{title}")
    public ResponseEntity<Object> fetchPost(@PathVariable("title") String title) {
        Post post = postService.fetchPostByTitle(title);
        return ResponseUtil.createResponse(HttpStatus.OK, post);
    }

    @PostMapping("/post")
    public ResponseEntity<Object> createPost(@RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
        return ResponseUtil.createResponse(HttpStatus.OK, null, POST_CREATE_SUCCESSFUL);
    }

    @PutMapping("/post")
    public ResponseEntity<Object> updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return ResponseUtil.createResponse(HttpStatus.NO_CONTENT, null, POST_UPDATE_SUCCESSFUL);
    }

    @DeleteMapping("/post/{title}")
    public ResponseEntity<Object> deletePost(@PathVariable("title") String title) {
        postService.deletePost(title);
        return ResponseUtil.createResponse(HttpStatus.NO_CONTENT, null, POST_DELETE_SUCCESSFUL);
    }
}