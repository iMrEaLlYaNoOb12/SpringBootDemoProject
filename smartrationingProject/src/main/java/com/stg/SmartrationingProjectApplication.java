package com.stg;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@SwaggerDefinition
public class SmartrationingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartrationingProjectApplication.class, args);
		
		
	}
	//dto
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}

}
