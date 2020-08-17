package com.kevin;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@MapperScan("com.kevin.service.sharding.mapper")
@EnableJpaRepositories("com.kevin.service.sharding.mapper")
@EnableSwagger2Doc
@Api("shardingjdbc服务application")
@EnableWebFlux
public class ShardingJdbcApplication {
	public static void main(String[] args) {

		SpringApplication.run(ShardingJdbcApplication.class, args);
	}
}
