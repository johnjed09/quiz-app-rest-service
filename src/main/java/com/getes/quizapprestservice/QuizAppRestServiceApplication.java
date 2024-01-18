package com.getes.quizapprestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class QuizAppRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppRestServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@Bean
	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();

		String elasticUser = "elastic"; // TODO: Environment variable.
		String elasticPass = "pIB4xZsWJ2DO1VkoUD*q"; // TODO: Environment variable.
		headers.setBasicAuth(elasticUser, elasticPass);

		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public QuizAppRepository repository() {
		return new QuizAppRepository();
	}

}
