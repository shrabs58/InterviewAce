package com.interviewace.dto;

import java.time.LocalDateTime;

public class DashboardResponse {

    private String userName;
    private boolean resumeUploaded;
    private long questionsAttempted;
    private long categoriesPracticed;
    private String latestQuestion;
    private LocalDateTime latestAttemptAt;

    public DashboardResponse() {
    }

    public DashboardResponse(String userName,
                             boolean resumeUploaded,
                             long questionsAttempted,
                             long categoriesPracticed,
                             String latestQuestion,
                             LocalDateTime latestAttemptAt) {

        this.userName = userName;
        this.resumeUploaded = resumeUploaded;
        this.questionsAttempted = questionsAttempted;
        this.categoriesPracticed = categoriesPracticed;
        this.latestQuestion = latestQuestion;
        this.latestAttemptAt = latestAttemptAt;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isResumeUploaded() {
        return resumeUploaded;
    }

    public long getQuestionsAttempted() {
        return questionsAttempted;
    }

    public long getCategoriesPracticed() {
        return categoriesPracticed;
    }

    public String getLatestQuestion() {
        return latestQuestion;
    }

    public LocalDateTime getLatestAttemptAt() {
        return latestAttemptAt;
    }

}