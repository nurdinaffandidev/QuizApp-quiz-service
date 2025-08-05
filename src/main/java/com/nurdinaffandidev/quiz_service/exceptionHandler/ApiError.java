package com.nurdinaffandidev.quiz_service.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ApiError class represents the standardized error response
 * structure sent back to clients when an exception occurs in the API.
 * <p>
 * It includes a human-readable error message, the HTTP status code,
 * and the timestamp when the error was generated.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    /**
     * A descriptive message explaining the error.
     * This helps clients understand what went wrong.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     * For example, 404 for Not Found, 500 for Internal Server Error.
     */
    private int status;

    /**
     * The timestamp indicating when the error occurred.
     * Useful for debugging and logging.
     */
    private LocalDateTime timestamp;
}
