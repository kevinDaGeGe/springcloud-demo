package com.kevin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableFeignClients
@MapperScan(basePackages = "com.kevin.service.mycat.mapper")
public class MycatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycatApplication.class, args);
	}
}
