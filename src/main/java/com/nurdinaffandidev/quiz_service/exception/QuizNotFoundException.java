package com.nurdinaffandidev.quiz_service.exception;

/**
 * Exception thrown when a Quiz entity is not found in the system.
 * Extends RuntimeException to allow unchecked exception handling.
 */
public class QuizNotFoundException extends RuntimeException {

    /**
     * Constructs a new QuizNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public QuizNotFoundException(String message) {
        super(message);
    }
}
