package com.interviewace.dto;

public class CategoryQuestionCountResponse {

    private String categoryName;
    private long questionCount;

    public CategoryQuestionCountResponse() {
    }

    public CategoryQuestionCountResponse(String categoryName, long questionCount) {
        this.categoryName = categoryName;
        this.questionCount = questionCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public long getQuestionCount() {
        return questionCount;
    }

}