package com.robokae.personalblog.repository;

import com.robokae.personalblog.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findByTitle(String title);
}
