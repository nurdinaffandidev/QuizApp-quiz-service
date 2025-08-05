package com.nurdinaffandidev.quiz_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A lightweight DTO class to represent a question without
 * exposing sensitive data like the correct answer.
 * Used primarily for transferring question data to clients.
 */
@Data
@AllArgsConstructor
public class QuestionWrapper {
    // Unique identifier for the question
    private Integer questionId;

    // First answer option
    private String option1;

    // Second answer option
    private String option2;

    // Third answer option
    private String option3;

    // Fourth answer option
    private String option4;

    // The question text/title
    private String questionTitle;
}
