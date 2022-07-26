package com.example.jpa_practice.Configuration;

import org.hibernate.boot.Metadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
@EnableWebMvc
public class Config {

    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getInfo())
            .select()
			.apis(RequestHandlerSelectors.basePackage("com.example.jpa_practice"))
			.paths(PathSelectors.any())
            .build();
	}
	
	private ApiInfo getInfo() {
		return new ApiInfoBuilder()
			.title("City Rest Apis")
			.description("City Rest api are located here")
			.version("1.0")
			.build();
	}

    
}
