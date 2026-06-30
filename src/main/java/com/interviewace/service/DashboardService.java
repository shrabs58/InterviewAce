package com.interviewace.service;

import com.interviewace.dto.DashboardResponse;
import com.interviewace.entity.InterviewAttempt;
import com.interviewace.entity.User;
import com.interviewace.exception.ResourceNotFoundException;
import com.interviewace.repository.InterviewAttemptRepository;
import com.interviewace.repository.ResumeRepository;
import com.interviewace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final InterviewAttemptRepository interviewAttemptRepository;

    public DashboardService(UserRepository userRepository,
                            ResumeRepository resumeRepository,
                            InterviewAttemptRepository interviewAttemptRepository) {

        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.interviewAttemptRepository = interviewAttemptRepository;
    }

    public DashboardResponse getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + userId));

        boolean resumeUploaded = resumeRepository.existsByUser(user);

        List<InterviewAttempt> attempts =
                interviewAttemptRepository.findByUser(user);

        long questionsAttempted = attempts.size();

        long categoriesPracticed = attempts.stream()
                .map(attempt -> attempt.getQuestion().getCategory().getId())
                .distinct()
                .count();

        InterviewAttempt latestAttempt = attempts.stream()
                .max(Comparator.comparing(InterviewAttempt::getAttemptedAt))
                .orElse(null);

        String latestQuestion = latestAttempt != null
                ? latestAttempt.getQuestion().getQuestion()
                : null;

        return new DashboardResponse(
                user.getFullName(),
                resumeUploaded,
                questionsAttempted,
                categoriesPracticed,
                latestQuestion,
                latestAttempt != null ? latestAttempt.getAttemptedAt() : null
        );
    }

}