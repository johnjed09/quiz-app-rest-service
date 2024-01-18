package com.getes.quizapprestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class QuizAppRepository {
	private static final Logger log = LoggerFactory.getLogger(QuizAppRestServiceApplication.class);

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private RestTemplate restTemplate;

	private final String ELASTIC_BASE_URL = "http://localhost:9200/"; // TODO: Move to an environment variable file.

	public ResponseEntity<String> elasticGetAll() {
		ResponseEntity<String> response = null;
		try {
			JsonObject elasticJson = Json.createObjectBuilder()
					.add("query", Json.createObjectBuilder()
							.add("match_all", "{}"))
					.build();

			String elasticJsonBody = new ObjectMapper().writeValueAsString(elasticJson);

			HttpEntity<String> entity = new HttpEntity<String>(elasticJsonBody, headers);

			response = restTemplate.exchange(
					ELASTIC_BASE_URL.concat("quizzes/_search"), HttpMethod.GET,
					entity,
					String.class);
		} catch (Exception e) {
			log.error("jed error", e);
		}

		return response;
	}

	public ResponseEntity<String> elasticGetQuiz(long id) {
		ResponseEntity<String> response = null;
		try {
			JsonObject elasticJson = Json.createObjectBuilder()
					.add("query", Json.createObjectBuilder()
							.add("simple_query_string", Json.createObjectBuilder()
									.add("query", Long.toString(id))
									.add("fields", Json.createArrayBuilder().add("quizNum"))))
					.build();

			String elasticJsonBody = elasticJson.toString();

			HttpEntity<String> entity = new HttpEntity<String>(elasticJsonBody, headers);

			response = restTemplate.exchange(
					ELASTIC_BASE_URL.concat("quizzes/_search"), HttpMethod.POST,
					entity,
					String.class);

		} catch (Exception e) {
			log.error("jed error", e);
		}

		return response;
	}
}
