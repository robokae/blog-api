package com.robokae.blog.util;

import com.robokae.blog.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Object> createResponse(HttpStatus status, Object data) {
        Response response = Response.builder()
                .status(status).data(data).build();
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<Object> createResponse(HttpStatus status, Object data, String message) {
        Response response = Response.builder()
                .status(status).data(data).message(message).build();
        return ResponseEntity.status(status).body(response);
    }
}
