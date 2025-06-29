package com.robokae.blog.service;

import com.robokae.blog.dto.PostRequest;
import com.robokae.blog.exception.PostException;
import com.robokae.blog.model.Status;
import com.robokae.blog.repository.PostRepository;
import com.robokae.blog.model.Post;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.robokae.blog.constant.PostConstants.*;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> fetchAllPosts() {
        return postRepository.findAll();
    }

    public Post fetchPostByTitle(String title) {
        return postRepository.findByTitle(title)
                .orElseThrow(() -> new PostException(POST_DOES_NOT_EXIST));
    }

    public void updatePost(Post updatedPost) {
        Post currentPost = postRepository.findByTitle(updatedPost.getTitle())
                .orElseThrow(() -> new PostException(POST_DOES_NOT_EXIST));
        postRepository.deleteById(currentPost.getId());
        updatedPost.setLastModified(new Date());
        postRepository.saveAndFlush(updatedPost);
    }

    public void createPost(PostRequest postRequest) {
        postRepository.findByTitle(postRequest.getTitle())
                .ifPresent(post -> {
                    throw new PostException(POST_TITLE_ALREADY_EXISTS);
                });

        Post post = mapToPost(postRequest);
        post.setCreatedAt(new Date());
        post.setStatus(Status.DRAFT);
        postRepository.save(post);
    }

    public void deletePost(String title) {
        Post post = postRepository.findByTitle(title)
                .orElseThrow(() -> new PostException(POST_DOES_NOT_EXIST));
        postRepository.deleteById(post.getId());
    }

    private Post mapToPost(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .author(postRequest.getAuthor())
                .content(postRequest.getContent())
                .tags(postRequest.getTags())
                .build();
    }
}