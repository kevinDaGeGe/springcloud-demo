package com.kevin.plugin.xss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = { "com.kevin.plugin.xss.mapper" })
@SpringBootApplication
@ServletComponentScan
public class XSSFilterAplication {

	public static void main(String[] args) {
		SpringApplication.run(XSSFilterAplication.class, args);
	}

}
