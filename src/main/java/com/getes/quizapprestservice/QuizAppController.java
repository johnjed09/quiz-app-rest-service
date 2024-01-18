package com.getes.quizapprestservice;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getAll() {
        return repository.elasticGetAll();
    }

    @GetMapping("/getQuizzes/{quizId}")
    public ResponseEntity<String> getQuiz(@PathVariable(value = "quizId") long id) {
        return repository.elasticGetQuiz(id);
    }

}
