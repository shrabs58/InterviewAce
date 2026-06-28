package com.interviewace.dto;

public class QuestionResponse {

    private Long id;
    private String question;
    private String answer;
    private String difficulty;
    private Long categoryId;
    private String categoryName;

    public QuestionResponse() {
    }

    public QuestionResponse(Long id, String question, String answer,
                            String difficulty, Long categoryId,
                            String categoryName) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}