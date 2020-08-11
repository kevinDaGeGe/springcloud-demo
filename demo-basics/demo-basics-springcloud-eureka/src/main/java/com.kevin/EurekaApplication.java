package com.kevin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:
 * @throws
 * @author: yu.han
 * @date: 2020/7/31 15:13
 */
@SpringBootApplication
@EnableEurekaServer
class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
