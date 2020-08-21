package com.kevin.service.sharding.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    private static Log log = LogFactory.getLog(HelloHandler.class);
        public Mono<ServerResponse> helloWebflux(ServerRequest request){
            System.out.println("执行helloWebflux方法");

            //ServerResponse 是对响应的封装，可以设置响应状态，响应头，响应正文。
            // 比如 ok 代表的是 200 响应码、MediaType 枚举是代表这文本内容类型、返回的是 String 的对象。
            return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                    .body(BodyInserters.fromObject("hello webflux!!!!!"));
        }
    }