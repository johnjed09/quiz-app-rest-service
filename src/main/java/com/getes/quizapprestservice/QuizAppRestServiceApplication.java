package com.getes.quizapprestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class QuizAppRestServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(QuizAppRestServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QuizAppRestServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		// builder.rootUri("http://localhost:9200");
		return builder.build();
	}

	@Bean
	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();

		String elasticUser = "elastic";
		String elasticPass = "pIB4xZsWJ2DO1VkoUD*q";
		headers.setBasicAuth(elasticUser, elasticPass);

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
