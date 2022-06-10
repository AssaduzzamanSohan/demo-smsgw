package com.example.demoProject.configuration;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.requestFactory(SimpleClientHttpRequestFactory::new).setConnectTimeout(Duration.ofSeconds(5))
				.setReadTimeout(Duration.ofSeconds(15)).build();
	}
}
