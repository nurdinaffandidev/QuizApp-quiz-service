package com.nurdinaffandidev.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizResponse {
    private Integer questionId;
    private String response;
}
