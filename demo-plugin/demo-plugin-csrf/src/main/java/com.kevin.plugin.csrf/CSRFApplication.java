/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.kevin.plugin.csrf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = { "com.kevin.plugin.csrf.mapper" })
@SpringBootApplication
@ServletComponentScan
public class CSRFApplication {

	public static void main(String[] args) {
		SpringApplication.run(CSRFApplication.class, args);
	}

}
