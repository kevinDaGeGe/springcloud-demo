package com.kevin.basics.zuul.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// @EnableSwagger2 表示开启 Swagger功能
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 生成API扫包范围
				.apis(RequestHandlerSelectors.basePackage("com.kevin.basics.controller")).paths(PathSelectors.any()).build();
	}

	// 创建API文档信息
	private ApiInfo apiInfo() {
		// title 文档标题
		// description 描述信息
		// termsOfServiceUrl 网址
		// version 版本号
		return new ApiInfoBuilder().title("微服务展示").description("springcloud微服务展示")
				.termsOfServiceUrl("http://www.kevin.com")
				// .contact(contact)
				.version("1.0").build();
	}

}