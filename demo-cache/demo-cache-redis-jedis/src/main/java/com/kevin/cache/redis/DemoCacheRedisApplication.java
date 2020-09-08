package com.kevin.cache.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;

@SpringBootApplication
@EnableSwagger2Doc
@org.springframework.data.redis.repository.configuration.EnableRedisRepositories
@Api("DemoCacheRedis服务application")
public class DemoCacheRedisApplication {
	public static void main(String[] args) {

		SpringApplication.run(DemoCacheRedisApplication.class, args);
	}
}