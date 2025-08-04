package com.nurdinaffandidev.quiz_service.repository;

import com.nurdinaffandidev.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
