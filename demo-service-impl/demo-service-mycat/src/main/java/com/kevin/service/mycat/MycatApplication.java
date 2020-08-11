package com.kevin.service.mycat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kevin.mycat.mapper")
public class MycatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycatApplication.class, args);
	}

}
