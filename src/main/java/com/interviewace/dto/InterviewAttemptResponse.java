package com.interviewace.dto;

import java.time.LocalDateTime;

public class InterviewAttemptResponse {

    private Long id;
    private String question;
    private String userAnswer;
    private String correctAnswer;
    private LocalDateTime attemptedAt;

    public InterviewAttemptResponse() {
    }

    public InterviewAttemptResponse(Long id,
                                    String question,
                                    String userAnswer,
                                    String correctAnswer,
                                    LocalDateTime attemptedAt) {

        this.id = id;
        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.attemptedAt = attemptedAt;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }
}