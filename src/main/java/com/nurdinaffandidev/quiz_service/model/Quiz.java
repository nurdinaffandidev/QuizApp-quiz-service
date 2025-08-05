package com.nurdinaffandidev.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents a Quiz entity with an ID, title, and list of question IDs.
 */
@Entity
@Data
public class Quiz {
    // Primary key for the quiz, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Title or name of the quiz
    private String title;

    /**
     * List of question IDs associated with this quiz.
     * Uses @ElementCollection to store a collection of basic types.
     */
    @ElementCollection
    private List<Integer> questionIds;
}
