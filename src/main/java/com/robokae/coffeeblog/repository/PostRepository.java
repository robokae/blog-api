package com.robokae.coffeeblog.repository;

import com.robokae.common.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}