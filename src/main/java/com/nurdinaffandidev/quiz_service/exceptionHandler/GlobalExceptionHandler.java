package com.nurdinaffandidev.quiz_service.exceptionHandler;

import com.nurdinaffandidev.quiz_service.exception.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * GlobalExceptionHandler handles exceptions thrown across the whole application
 * and provides consistent error responses.
 * <p>
 * It uses @RestControllerAdvice to apply exception handling globally to all controllers.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all generic exceptions that are not explicitly handled elsewhere.
     * Returns a generic error message with HTTP 500 Internal Server Error status.
     *
     * @param exception The exception thrown
     * @return ResponseEntity with error message and HTTP status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        // Return a plain message with HTTP 500 status for unexpected exceptions
        return new ResponseEntity<>(
                "Internal Server Error: " + exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles QuizNotFoundException when a quiz entity is not found.
     * Returns an ApiError object with details and HTTP 404 Not Found status.
     *
     * @param exception The QuizNotFoundException thrown
     * @return ResponseEntity containing ApiError and HTTP status 404
     */
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ApiError> handleQuizNotFound(QuizNotFoundException exception) {
        // Create ApiError with message, 404 status code, and current timestamp
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());

        // Return the ApiError object wrapped in ResponseEntity with 404 status
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
