package com.getes.quizapprestservice;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class QuizAppController {
    private static final Logger log = LoggerFactory.getLogger(QuizAppRestServiceApplication.class);

    @Autowired
    QuizAppRepository repository;

    @GetMapping("/testing")
    public String testConsumer() {
        log.info("Jed Testing");
        return "testing";
    }

    @GetMapping("/getQuizzes")
    public ResponseEntity<String> getAll() throws JsonProcessingException {
        return repository.elasticGetAll();
    }

    @PostMapping("/getQuiz")
    public ResponseEntity<String> getQuiz(@RequestBody HttpEntity<String> httpEntity) {
        return repository.elasticGetQuiz(httpEntity.getBody());
    }

}
