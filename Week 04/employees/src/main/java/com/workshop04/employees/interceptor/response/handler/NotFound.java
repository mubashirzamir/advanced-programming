package com.workshop04.employees.interceptor.response.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

public class NotFound implements IResponseHandler {
    public static final int STATUS_CODE = 404;

    @Override
    public boolean supports(int statusCode) {
        return statusCode == STATUS_CODE;
    }

    @Override
    public ResponseEntity<Object> handleResponse(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        return new ResponseEntity<>("Not Found.", HttpStatus.NOT_FOUND);
    }
}
