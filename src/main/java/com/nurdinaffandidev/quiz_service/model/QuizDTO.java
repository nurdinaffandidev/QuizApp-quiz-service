package com.nurdinaffandidev.quiz_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for creating a Quiz.
 * Holds the category name, number of questions, and quiz title.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    // The category name for the quiz questions
    private String categoryName;

    // Number of questions requested for the quiz
    private Integer numQuestions;

    // Title of the quiz
    private String title;
}
