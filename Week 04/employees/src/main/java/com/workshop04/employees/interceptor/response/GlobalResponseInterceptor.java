package com.workshop04.employees.interceptor.response;

import com.workshop04.employees.interceptor.response.handler.IResponseHandler;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class GlobalResponseInterceptor implements ResponseBodyAdvice<Object> {
    private final List<IResponseHandler> handlers;

    public GlobalResponseInterceptor(List<IResponseHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (response instanceof ServletServerHttpResponse) {
            int statusCode = ((ServletServerHttpResponse) response).getServletResponse().getStatus();

            Optional<ResponseEntity<Object>> optional = this.handlers.stream()
                    .filter(handler -> handler.supports(statusCode))
                    .findFirst()
                    .map(handler -> handler.handleResponse(response,
                            returnType,
                            selectedContentType,
                            selectedConverterType,
                            request,
                            response));

            if (optional.isPresent()) {
                return optional;
            }
        }

        return body;
    }
}
