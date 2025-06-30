package com.robokae.blog.service;

import com.robokae.blog.dto.PostRequest;
import com.robokae.blog.exception.PostException;
import com.robokae.blog.model.Post;
import com.robokae.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setup() {
        postService = new PostService(postRepository);
        Post testPost = Post.builder()
                .title("title").author("author").content("content")
                .build();
        when(postRepository.findByTitle("title")).thenReturn(Optional.of(testPost));
    }

    @Test
    void fetchPostByTitle_withExistingTitle_returnsPost() {
        Post post = postService.fetchPostByTitle("title");
        assertEquals("title", post.getTitle());
        assertEquals("author", post.getAuthor());
        assertEquals("content", post.getContent());
    }

    @Test
    void createPost_withDuplicateTitle_throwsException() {
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("title");
        assertThrows(PostException.class, () -> postService.createPost(postRequest));
    }
}
