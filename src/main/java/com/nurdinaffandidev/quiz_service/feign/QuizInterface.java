package com.nurdinaffandidev.quiz_service.feign;

import com.nurdinaffandidev.quiz_service.model.Question;
import com.nurdinaffandidev.quiz_service.model.QuestionWrapper;
import com.nurdinaffandidev.quiz_service.model.QuizResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUIZAPP-QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate-question-ids")
    public ResponseEntity<List<Integer>> generateQuestionsIds(@RequestParam String category, @RequestParam int numQuestions);

    @PostMapping("question/retrieve-wrapper-questions")
    public ResponseEntity<List<QuestionWrapper>> retrieveWrapperQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/get-score")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses);
}
