package com.interviewace.controller;

import com.interviewace.dto.InterviewAttemptRequest;
import com.interviewace.dto.InterviewAttemptResponse;
import com.interviewace.service.InterviewAttemptService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-attempts")
public class InterviewAttemptController {

    private final InterviewAttemptService interviewAttemptService;

    public InterviewAttemptController(InterviewAttemptService interviewAttemptService) {
        this.interviewAttemptService = interviewAttemptService;
    }

    // Submit Interview Attempt
    @PostMapping
    public InterviewAttemptResponse submitAttempt(
            @Valid @RequestBody InterviewAttemptRequest request) {

        return interviewAttemptService.submitAttempt(request);
    }

    // Get Attempt By ID
    @GetMapping("/{id}")
    public InterviewAttemptResponse getAttemptById(@PathVariable Long id) {

        return interviewAttemptService.getAttemptById(id);
    }

    // Get Attempts By User
    @GetMapping("/user/{userId}")
    public List<InterviewAttemptResponse> getAttemptsByUser(
            @PathVariable Long userId) {

        return interviewAttemptService.getAttemptsByUser(userId);
    }

    // Get Attempts By Question
    @GetMapping("/question/{questionId}")
    public List<InterviewAttemptResponse> getAttemptsByQuestion(
            @PathVariable Long questionId) {

        return interviewAttemptService.getAttemptsByQuestion(questionId);
    }

    // Delete Attempt
    @DeleteMapping("/{id}")
    public void deleteAttempt(@PathVariable Long id) {

        interviewAttemptService.deleteAttempt(id);
    }

}