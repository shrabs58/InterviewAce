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

    @PostMapping
    public QuestionResponse createQuestion(@Valid @RequestBody QuestionRequest request) {
        return questionService.createQuestion(request);
    }

    @GetMapping
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}