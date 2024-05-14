package com.robokae.blog.controller;

import com.robokae.blog.service.PostService;
import com.robokae.common.model.Post;
import com.robokae.common.model.Response;
import com.robokae.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffeeblog")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Response> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        Response response = ResponseUtil.getResponse(HttpStatus.OK, "", posts);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/post/{title}")
    public ResponseEntity<Response> getPostByTitle(@PathVariable String title) {
        Post post = postService.getPostByTitle(title);
        Response response = ResponseUtil.getResponse(HttpStatus.OK, "", post);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/post")
    public ResponseEntity<Response> createPost(@RequestBody Post post) {
        postService.savePost(post);
        Response response = ResponseUtil.getResponse(HttpStatus.OK, "Successfully created post", "");
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/post/{title}")
    public ResponseEntity<Response> updatePost(@PathVariable String title, @RequestBody Post post) {
        postService.updatePost(title, post);
        Response response = ResponseUtil.getResponse(HttpStatus.CREATED, "Successfully updated post", "");
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/post/{title}")
    public ResponseEntity<Response> deletePost(@PathVariable String title) {
        postService.deletePost(title);
        Response response = ResponseUtil.getResponse(HttpStatus.OK, "Successfully deleted post", "");
        return new ResponseEntity<>(response, response.getStatus());
    }
}