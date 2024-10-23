package com.workshop04.employees.interceptor.response;

import com.workshop04.employees.interceptor.response.handler.IResponseHandler;
import com.workshop04.employees.interceptor.response.handler.NotFound;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ResponseHandlerConfig {

    @Bean
    public List<IResponseHandler> responseHandlers() {
        return List.of(new NotFound());
    }

    @Bean
    public GlobalResponseInterceptor customResponseBodyAdvice(List<IResponseHandler> handlers) {
        return new GlobalResponseInterceptor(handlers);
    }
}
