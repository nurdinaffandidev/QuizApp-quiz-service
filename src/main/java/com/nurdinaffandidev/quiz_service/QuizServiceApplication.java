package com.nurdinaffandidev.quiz_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main entry point for the Quiz Service Spring Boot application.
 * Enables Feign clients for inter-service communication.
 */
@SpringBootApplication
@EnableFeignClients  // Enables scanning for interfaces annotated with @FeignClient
public class QuizServiceApplication {

	/**
	 * Main method to launch the Quiz Service application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}

}
