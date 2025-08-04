package com.nurdinaffandidev.quiz_service.service;

import com.nurdinaffandidev.quiz_service.exception.QuizNotFoundException;
import com.nurdinaffandidev.quiz_service.model.Question;
import com.nurdinaffandidev.quiz_service.model.QuestionWrapper;
import com.nurdinaffandidev.quiz_service.model.Quiz;
import com.nurdinaffandidev.quiz_service.model.QuizResponse;
import com.nurdinaffandidev.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(String category, Integer numQuestions, String title) {
        // connect to question-service to retrieve questions
        // - via RestTemplate
        // - through Feign Client, Service Discovery, Eureka Server

//        List<Question> questions = questionService.generateQuestions(category, numQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
//        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return quiz;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id= " + id + " not found."));

        List<QuestionWrapper> cleanedQuestions = new ArrayList<>();
        quiz.getQuestions().forEach( question -> {
            QuestionWrapper cleanedQn = new QuestionWrapper(
                    question.getId(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4(),
                    question.getQuestionTitle()
                );
            cleanedQuestions.add(cleanedQn);
        });
        return cleanedQuestions;
    }

    public Integer calculateResult(Integer quizId, List<QuizResponse> responses) {

        // connect to question-service to retrieve questions
        // - via RestTemplate
        // - through Service Discovery

        int correctAnswer = 0;

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id= " + quizId + " not found."));

        List<Question> quizQuestions = quiz.getQuestions();
        System.out.println("quizQuestions= ");
        quizQuestions.forEach(System.out::println);

        for(QuizResponse response : responses) {
            System.out.println("response= " + response);
            Optional<Question> questionToCheck = quizQuestions.stream()
                    .filter(qn -> qn.getId() == response.getQuestionId())
                    .findFirst();
            if(questionToCheck.isPresent()) {
                if (response.getResponse().equals(questionToCheck.get().getCorrectAnswer())) {
                    correctAnswer++;
                }
            }
        }
        return correctAnswer;
    }

}
