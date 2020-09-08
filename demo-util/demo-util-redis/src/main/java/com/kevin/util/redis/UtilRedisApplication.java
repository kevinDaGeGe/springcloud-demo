package com.kevin.util.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;

@SpringBootApplication(scanBasePackages = {"com.kevin.util.redis","org.springframework.data.redis.listener"})
@EnableSwagger2Doc
@org.springframework.data.redis.repository.configuration.EnableRedisRepositories
@Api("UtilRedis服务application")
public class UtilRedisApplication {
	public static void main(String[] args) {

		SpringApplication.run(UtilRedisApplication.class, args);
	}
}