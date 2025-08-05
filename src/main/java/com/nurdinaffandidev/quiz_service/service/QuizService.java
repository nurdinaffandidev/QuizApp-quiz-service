package com.nurdinaffandidev.quiz_service.service;

import com.nurdinaffandidev.quiz_service.exception.QuizNotFoundException;
import com.nurdinaffandidev.quiz_service.feign.QuizInterface;
import com.nurdinaffandidev.quiz_service.model.QuestionWrapper;
import com.nurdinaffandidev.quiz_service.model.Quiz;
import com.nurdinaffandidev.quiz_service.model.QuizResponse;
import com.nurdinaffandidev.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing quizzes.
 * Handles creation, retrieval, and scoring of quizzes.
 */
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    /**
     * Constructs a QuizService with the specified repository and Feign client.
     *
     * @param quizRepository the repository for Quiz persistence
     * @param quizInterface  the Feign client interface to communicate with Question Service
     */
    @Autowired
    public QuizService(QuizRepository quizRepository, QuizInterface quizInterface) {
        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    /**
     * Creates a new quiz by fetching random question IDs from Question Service,
     * then saves the quiz with the given title and question IDs.
     *
     * @param category     the category of questions to include in the quiz
     * @param numQuestions the number of questions to include
     * @param title        the title of the quiz
     * @return the saved Quiz object
     */
    public Quiz createQuiz(String category, Integer numQuestions, String title) {
        List<Integer> questionsIds = quizInterface.generateQuestionsIds(category, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionsIds);
        quizRepository.save(quiz);
        return quiz;
    }

    /**
     * Retrieves all quizzes stored in the repository.
     *
     * @return list of all quizzes
     */
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    /**
     * Retrieves the questions wrapped for a quiz specified by its ID.
     * If the quiz is not found, throws QuizNotFoundException.
     *
     * @param id the quiz ID
     * @return list of QuestionWrapper objects for the quiz questions
     */
    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id= " + id + " not found."));
        return quizInterface.retrieveWrapperQuestions(quiz.getQuestionIds()).getBody();
    }

    /**
     * Calculates the score for a submitted list of quiz responses by delegating
     * to the Question Service via the Feign client.
     *
     * @param responses list of QuizResponse objects representing user answers
     * @return the total number of correct answers
     */
    public Integer calculateResult(List<QuizResponse> responses) {
        return quizInterface.getScore(responses).getBody();
    }

}
