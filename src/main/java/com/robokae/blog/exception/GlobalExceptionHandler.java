package com.robokae.blog.exception;

import com.robokae.blog.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PostException.class)
    protected ResponseEntity<Object> handlePostException(PostException e) {
        return ResponseUtil.createResponse(HttpStatus.BAD_REQUEST, null, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        return ResponseUtil.createResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
    }
}
