package com.interviewace.controller;

import com.interviewace.dto.QuestionRequest;
import com.interviewace.dto.QuestionResponse;
import com.interviewace.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Create Question
    @PostMapping
    public QuestionResponse createQuestion(@Valid @RequestBody QuestionRequest request) {
        return questionService.createQuestion(request);
    }

    // Get All Questions
    @GetMapping
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Get Question By ID
    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    // Update Question
    @PutMapping("/{id}")
    public QuestionResponse updateQuestion(@PathVariable Long id,
                                           @Valid @RequestBody QuestionRequest request) {
        return questionService.updateQuestion(id, request);
    }

    // Delete Question
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    // Filter Questions by Category
    @GetMapping("/category/{categoryId}")
    public List<QuestionResponse> getQuestionsByCategory(@PathVariable Long categoryId) {
        return questionService.getQuestionsByCategory(categoryId);
    }

    // Search Questions
    @GetMapping("/search")
    public List<QuestionResponse> searchQuestions(@RequestParam String keyword) {
        return questionService.searchQuestions(keyword);
    }
}