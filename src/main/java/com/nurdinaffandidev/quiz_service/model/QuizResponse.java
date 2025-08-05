package com.nurdinaffandidev.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a response for a quiz question.
 * Contains the question ID and the user's response.
 */
@Data
@RequiredArgsConstructor
public class QuizResponse {
    // The ID of the question being answered
    private Integer questionId;

    // The user's answer to the question
    private String response;
}
