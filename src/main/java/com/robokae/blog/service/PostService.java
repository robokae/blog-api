package com.robokae.blog.service;

import com.robokae.blog.repository.PostRepository;
import com.robokae.blog.model.Post;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
        return postRepository.findByTitle(title).orElseThrow(() -> new NoSuchElementException(POST_DOES_NOT_EXIST));
    }

    public void updatePost(Post updatedPost) {
        Post currentPost = postRepository.findByTitle(updatedPost.getTitle()).orElseThrow(() ->
                new NoSuchElementException(POST_DOES_NOT_EXIST));
        postRepository.deleteById(currentPost.getId());
        updatedPost.setLastModified(new Date());
        postRepository.saveAndFlush(updatedPost);
    }

    public void createPost(Post post) {
        postRepository.findByTitle(post.getTitle()).ifPresent(p -> {
            throw new EntityExistsException(DUPLICATE_TITLE);
        });
        post.setCreatedAt(new Date());
        postRepository.save(post);
    }

    public void deletePost(String title) {
        Post post = postRepository.findByTitle(title).orElseThrow(() -> new NoSuchElementException(POST_DOES_NOT_EXIST));
        postRepository.deleteById(post.getId());
    }
}