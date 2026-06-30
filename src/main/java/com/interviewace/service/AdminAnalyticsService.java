package com.interviewace.service;

import com.interviewace.dto.AdminAnalyticsResponse;
import com.interviewace.dto.CategoryQuestionCountResponse;
import com.interviewace.entity.Category;
import com.interviewace.entity.Question;
import com.interviewace.enums.Role;
import com.interviewace.repository.CategoryRepository;
import com.interviewace.repository.InterviewAttemptRepository;
import com.interviewace.repository.QuestionRepository;
import com.interviewace.repository.ResumeRepository;
import com.interviewace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAnalyticsService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final ResumeRepository resumeRepository;
    private final InterviewAttemptRepository interviewAttemptRepository;

    public AdminAnalyticsService(UserRepository userRepository,
                                 CategoryRepository categoryRepository,
                                 QuestionRepository questionRepository,
                                 ResumeRepository resumeRepository,
                                 InterviewAttemptRepository interviewAttemptRepository) {

        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.resumeRepository = resumeRepository;
        this.interviewAttemptRepository = interviewAttemptRepository;
    }

    public AdminAnalyticsResponse getAnalytics() {

        long totalStudents = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.STUDENT)
                .count();

        long totalCategories = categoryRepository.count();

        long totalQuestions = questionRepository.count();

        long totalResumeUploads = resumeRepository.count();

        long totalInterviewAttempts = interviewAttemptRepository.count();

        List<CategoryQuestionCountResponse> questionsPerCategory = new ArrayList<>();

        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {

            long questionCount = questionRepository.findAll()
                    .stream()
                    .filter(question ->
                            question.getCategory().getId().equals(category.getId()))
                    .count();

            questionsPerCategory.add(
                    new CategoryQuestionCountResponse(
                            category.getName(),
                            questionCount
                    )
            );
        }

        return new AdminAnalyticsResponse(
                totalStudents,
                totalCategories,
                totalQuestions,
                totalResumeUploads,
                totalInterviewAttempts,
                questionsPerCategory
        );
    }

}