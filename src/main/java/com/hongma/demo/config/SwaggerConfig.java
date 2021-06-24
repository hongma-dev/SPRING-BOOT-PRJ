package com.hongma.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	private static final Contact DEFALUT_CONTACT = new Contact("hongma",
			"http://www.jojoneconsulting.co.kr", "lovely4750@naver.com");
	
	private static final ApiInfo DEFALUT_API_INFO = new ApiInfo("AweSome Api Title",
			"My User management REST API service", "1,0", "urn:tos",
			DEFALUT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
	
	private static final Set<String> DEFALUT_PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json","application/xml")
			);
	
	@Bean
	public Docket api() {
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFALUT_API_INFO)
				.produces(DEFALUT_PRODUCES_AND_CONSUMES)
				.consumes(DEFALUT_PRODUCES_AND_CONSUMES);
		
		
		return docket;
	}
}
