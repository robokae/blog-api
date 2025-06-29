package com.robokae.blog.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostRequest {

    private String title;
    private String author;
    private String content;
    private List<String> tags;
}
