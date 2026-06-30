package com.interviewace.dto;

import java.util.List;

public class AdminAnalyticsResponse {

    private long totalStudents;
    private long totalCategories;
    private long totalQuestions;
    private long totalResumeUploads;
    private long totalInterviewAttempts;

    private List<CategoryQuestionCountResponse> questionsPerCategory;

    public AdminAnalyticsResponse() {
    }

    public AdminAnalyticsResponse(long totalStudents,
                                  long totalCategories,
                                  long totalQuestions,
                                  long totalResumeUploads,
                                  long totalInterviewAttempts,
                                  List<CategoryQuestionCountResponse> questionsPerCategory) {

        this.totalStudents = totalStudents;
        this.totalCategories = totalCategories;
        this.totalQuestions = totalQuestions;
        this.totalResumeUploads = totalResumeUploads;
        this.totalInterviewAttempts = totalInterviewAttempts;
        this.questionsPerCategory = questionsPerCategory;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalCategories() {
        return totalCategories;
    }

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public long getTotalResumeUploads() {
        return totalResumeUploads;
    }

    public long getTotalInterviewAttempts() {
        return totalInterviewAttempts;
    }

    public List<CategoryQuestionCountResponse> getQuestionsPerCategory() {
        return questionsPerCategory;
    }

}