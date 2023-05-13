package com.robokae.coffeeblog.repository;

import com.robokae.common.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoffeeBlogRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
}