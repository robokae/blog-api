package com.robokae.coffeeblog.service;

import com.robokae.coffeeblog.repository.CoffeeBlogRepository;
import com.robokae.common.model.Post;
import com.robokae.common.model.PostError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.robokae.common.model.PostError.POST_ALREADY_EXISTS;
import static com.robokae.common.model.PostError.POST_DOES_NOT_EXIST;

@Service
public class CoffeeBlogService {

    @Autowired
    private CoffeeBlogRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostByTitle(String title) {
        return postRepository.findByTitle(title).orElseThrow(() ->
                new NoSuchElementException(getErrorMessage(POST_DOES_NOT_EXIST, title)));
    }

    public void updatePost(String title, Post updatedPost) {
        Post currentPost = postRepository.findByTitle(title).orElseThrow(() ->
                new NoSuchElementException(getErrorMessage(POST_DOES_NOT_EXIST, title)));
        postRepository.deleteById(currentPost.getId());
        postRepository.saveAndFlush(updatedPost);
    }

    public void savePost(Post post) {
        postRepository.findByTitle(post.getTitle()).ifPresent(p -> {
            throw new EntityExistsException(getErrorMessage(POST_ALREADY_EXISTS, p.getTitle()));
        });
        postRepository.save(post);
    }

    public void deletePost(String title) {
        Post post = postRepository.findByTitle(title).orElseThrow(() ->
            new NoSuchElementException(getErrorMessage(POST_DOES_NOT_EXIST, title)));
        postRepository.deleteById(post.getId());
    }

    private String getErrorMessage(PostError postError, String title) {
        return switch (postError) {
            case POST_DOES_NOT_EXIST -> "No post exists with the title '" + title + "'";
            case POST_ALREADY_EXISTS -> "Post with title " + title + "' already exists";
        };
    }
}