package com.kevin.service.sharding.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {
    /**
     * 访问http://localhost:9999/hello
     * @param helloHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routerWebflux(HelloHandler helloHandler){

        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        helloHandler::helloWebflux);

    }


}