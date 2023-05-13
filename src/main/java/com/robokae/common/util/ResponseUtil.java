package com.robokae.common.util;

import com.robokae.common.model.Response;
import org.springframework.http.HttpStatus;

public class ResponseUtil {

    public static Response getResponse(HttpStatus status, String message, Object data) {
        return new Response(status, message, data);
    }
}
