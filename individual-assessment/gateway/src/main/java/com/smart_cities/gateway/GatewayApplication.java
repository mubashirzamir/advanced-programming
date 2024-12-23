package com.smart_cities.gateway;

import com.smart_cities.gateway.config.UriConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(UriConfiguration.class)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        return builder.routes()
                .route("provider-01-routes", r -> r
                        .path("/provider/1/**")
                        .filters(GatewayApplication.applyDefaultRouteFilters())
                        .uri(uriConfiguration.getProvider01()))
                .route("provider-02-routes", r -> r
                        .path("/provider/2/**")
                        .filters(GatewayApplication.applyDefaultRouteFilters())
                        .uri(uriConfiguration.getProvider02()))
                .route("provider-03-routes", r -> r
                        .path("/provider/3/**")
                        .filters(GatewayApplication.applyDefaultRouteFilters())
                        .uri(uriConfiguration.getProvider03()))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("Service is currently unavailable");
    }

    /**
     * Strips the prefix i.e. the two segments of the URI /provider/{id}
     * Sets the circuit breaker to forward to the fallback URI /fallback
     */
    public static Function<GatewayFilterSpec, UriSpec> applyDefaultRouteFilters() {
        return f -> f.stripPrefix(2)
                .circuitBreaker((config -> config.setFallbackUri("forward:/fallback")));
    }
}