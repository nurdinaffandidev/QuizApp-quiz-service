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

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizInterface quizInterface) {
        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    public Quiz createQuiz(String category, Integer numQuestions, String title) {
        List<Integer> questionsIds = quizInterface.generateQuestionsIds(category, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionsIds);
        quizRepository.save(quiz);
        return quiz;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id= " + id + " not found."));
        return quizInterface.retrieveWrapperQuestions(quiz.getQuestionIds()).getBody();
    }

    public Integer calculateResult(List<QuizResponse> responses) {
        return quizInterface.getScore(responses).getBody();
    }

}
