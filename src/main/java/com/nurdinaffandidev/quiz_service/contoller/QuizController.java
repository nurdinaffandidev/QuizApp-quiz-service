package com.nurdinaffandidev.quiz_service.contoller;

import com.nurdinaffandidev.quiz_service.model.QuestionWrapper;
import com.nurdinaffandidev.quiz_service.model.Quiz;
import com.nurdinaffandidev.quiz_service.model.QuizDTO;
import com.nurdinaffandidev.quiz_service.model.QuizResponse;
import com.nurdinaffandidev.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz-app/quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // get all quizzes
    @GetMapping("/allQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    // create quiz
    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(
                quizService.createQuiz(
                        quizDTO.getCategoryName(),
                        quizDTO.getNumQuestions(),
                        quizDTO.getTitle()
                ),
                HttpStatus.OK
        );
    }

    // get questions from quiz
    @GetMapping(value = "/", params = "id")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam int id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id), HttpStatus.OK);
    }

    @PostMapping(value = "/submit/", params = "quizId")
    public ResponseEntity<Integer> submitAnswer(@RequestParam Integer quizId , @RequestBody List<QuizResponse> responses) {
        return new ResponseEntity<>(quizService.calculateResult(quizId, responses), HttpStatus.OK);
    }
}
