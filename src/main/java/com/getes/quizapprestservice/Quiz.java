package com.getes.quizapprestservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Quiz(String quizNum, String question, String cluster_name) {

}
