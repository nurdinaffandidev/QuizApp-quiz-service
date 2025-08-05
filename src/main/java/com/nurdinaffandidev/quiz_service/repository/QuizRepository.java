package com.nurdinaffandidev.quiz_service.repository;

import com.nurdinaffandidev.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Quiz entities.
 * Extends JpaRepository to provide CRUD operations and more.
 */
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    // No additional methods needed; inherits standard CRUD methods from JpaRepository
}
