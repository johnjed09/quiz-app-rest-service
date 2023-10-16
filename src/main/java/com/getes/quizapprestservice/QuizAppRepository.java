package com.getes.quizapprestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class QuizAppRepository {
    // private static final Logger log =
    // LoggerFactory.getLogger(QuizAppRestServiceApplication.class);
    @Autowired
    private HttpHeaders headers;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> elasticGetAll() throws JsonProcessingException {
        // log.info("Jed Testing");

        JsonObject elasticJson = Json.createObjectBuilder()
                .add("query", Json.createObjectBuilder()
                        .add("match_all", "{}"))
                .build();

        String elasticJsonBody = new ObjectMapper().writeValueAsString(elasticJson);

        HttpEntity<String> entity = new HttpEntity<String>(elasticJsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:9200/quizzes/_search", HttpMethod.GET,
                entity,
                String.class);

        return response;
    }

    public ResponseEntity<String> elasticGetQuiz(String requestJson) {
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:9200/quizzes/_search", HttpMethod.POST,
                entity,
                String.class);

        return response;
    }
}
