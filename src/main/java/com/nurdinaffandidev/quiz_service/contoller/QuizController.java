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

/**
 * REST controller for handling quiz-related operations.
 * Exposes endpoints for creating quizzes, retrieving quizzes,
 * fetching quiz questions, and submitting quiz responses.
 */
@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    /**
     * Constructor to inject QuizService dependency.
     *
     * @param quizService service layer handling quiz business logic
     */
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    /**
     * GET endpoint to retrieve all quizzes.
     *
     * @return ResponseEntity containing list of all quizzes and HTTP status 200
     */
    @GetMapping("/allQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        // Call service layer to fetch all quizzes and return as response
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    /**
     * POST endpoint to create a new quiz.
     *
     * @param quizDTO data transfer object containing quiz creation parameters:
     *                categoryName, number of questions, and title
     * @return ResponseEntity containing created Quiz object and HTTP status 200
     */
    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO) {
        // Delegate quiz creation to service layer using values from DTO
        Quiz createdQuiz = quizService.createQuiz(
                quizDTO.getCategoryName(),
                quizDTO.getNumQuestions(),
                quizDTO.getTitle()
        );
        // Return created quiz as response
        return new ResponseEntity<>(createdQuiz, HttpStatus.OK);
    }

    /**
     * GET endpoint to fetch questions of a specific quiz by its ID.
     *
     * @param id the quiz ID passed as request parameter
     * @return ResponseEntity containing list of QuestionWrapper and HTTP status 200
     */
    @GetMapping(value = "/", params = "id")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam int id) {
        // Fetch quiz questions wrapped in simplified QuestionWrapper objects
        List<QuestionWrapper> questions = quizService.getQuizQuestions(id);
        // Return questions in response entity
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * POST endpoint to submit quiz answers and calculate the result.
     *
     * @param responses list of QuizResponse objects containing questionId and user response
     * @return ResponseEntity containing the integer score and HTTP status 200
     */
    @PostMapping(value = "/submit")
    public ResponseEntity<Integer> submitAnswer(@RequestBody List<QuizResponse> responses) {
        // Calculate score by delegating to service layer
        int score = quizService.calculateResult(responses);
        // Return calculated score in response
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
