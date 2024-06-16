package com.robokae.common.exception;

import com.robokae.common.model.Response;
import com.robokae.common.util.ResponseUtil;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Response> handleNoSuchElement(NoSuchElementException e) {
        Response response = ResponseUtil.getResponse(HttpStatus.NOT_FOUND, e.getMessage(), "");
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Response> handleElementAlreadyExists(EntityExistsException e) {
        Response response = ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, e.getMessage(), "");
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Response> handleException(Exception e) {
        Response response = ResponseUtil.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "");
        return new ResponseEntity<>(response, response.getStatus());
    }
}
