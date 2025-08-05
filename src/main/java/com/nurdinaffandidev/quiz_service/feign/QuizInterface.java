package com.nurdinaffandidev.quiz_service.feign;

import com.nurdinaffandidev.quiz_service.model.QuestionWrapper;
import com.nurdinaffandidev.quiz_service.model.QuizResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign client interface to communicate with the QuizApp Question Service.
 * This interface allows the quiz service to call question service endpoints
 * over HTTP using declarative REST clients.
 */
@FeignClient("QUIZAPP-QUESTION-SERVICE")
public interface QuizInterface {

    /**
     * Calls the question service to generate a list of random question IDs for a given category.
     *
     * @param category     The category of questions to generate.
     * @param numQuestions The number of question IDs to generate.
     * @return ResponseEntity wrapping a List of Integer question IDs.
     */
    @GetMapping("question/generate-question-ids")
    public ResponseEntity<List<Integer>> generateQuestionsIds(
            @RequestParam String category,
            @RequestParam int numQuestions);

    /**
     * Calls the question service to retrieve wrapped question details for given question IDs.
     *
     * @param questionIds List of question IDs to retrieve details for.
     * @return ResponseEntity wrapping a List of QuestionWrapper objects containing question details.
     */
    @PostMapping("question/retrieve-wrapper-questions")
    public ResponseEntity<List<QuestionWrapper>> retrieveWrapperQuestions(
            @RequestBody List<Integer> questionIds);

    /**
     * Calls the question service to calculate the score based on a list of quiz responses.
     *
     * @param responses List of QuizResponse objects representing user answers.
     * @return ResponseEntity wrapping an Integer representing the calculated score.
     */
    @PostMapping("question/get-score")
    public ResponseEntity<Integer> getScore(
            @RequestBody List<QuizResponse> responses);
}
