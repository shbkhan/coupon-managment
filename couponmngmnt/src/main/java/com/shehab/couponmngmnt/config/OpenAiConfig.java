package com.shehab.couponmngmnt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAiConfig {

	@Bean
	OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Coupon managment").description("Project to manage coupons")
				.version("v1.0").contact(new Contact().name("Shehab Sajid Khan").email("shehabsajid@gmail.com")
						.url("https://linkedin.com/in/shbkhan")));
	}
}
