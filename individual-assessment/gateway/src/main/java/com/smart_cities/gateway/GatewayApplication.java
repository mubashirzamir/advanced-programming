package com.smart_cities.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("city-routes", r -> r
                        .path("/city/**")
                        .filters(f -> f.prefixPath("/api"))
                        .uri(System.getenv().getOrDefault("CITY_ROUTE_URI", "http://localhost:9080")))
                .route("provider-01-routes", r -> r
                        .path("/provider/1/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(System.getenv().getOrDefault("PROVIDER_01_ROUTE_URI", "http://localhost:9081")))
                .route("provider-02-routes", r -> r
                        .path("/provider/2/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(System.getenv().getOrDefault("PROVIDER_02_ROUTE_URI", "http://localhost:9082")))
                .route("provider-03-routes", r -> r
                        .path("/provider/3/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri(System.getenv().getOrDefault("PROVIDER_03_ROUTE_URI", "http://localhost:9083")))
                .build();
    }
}