package com.interviewace.service;

import com.interviewace.dto.InterviewAttemptRequest;
import com.interviewace.dto.InterviewAttemptResponse;
import com.interviewace.entity.InterviewAttempt;
import com.interviewace.entity.Question;
import com.interviewace.entity.User;
import com.interviewace.exception.ResourceNotFoundException;
import com.interviewace.repository.InterviewAttemptRepository;
import com.interviewace.repository.QuestionRepository;
import com.interviewace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewAttemptService {

    private final InterviewAttemptRepository interviewAttemptRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public InterviewAttemptService(InterviewAttemptRepository interviewAttemptRepository,
                                   UserRepository userRepository,
                                   QuestionRepository questionRepository) {

        this.interviewAttemptRepository = interviewAttemptRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    // Submit Attempt
    public InterviewAttemptResponse submitAttempt(InterviewAttemptRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + request.getUserId()));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + request.getQuestionId()));

        InterviewAttempt interviewAttempt = new InterviewAttempt();

        interviewAttempt.setUser(user);
        interviewAttempt.setQuestion(question);
        interviewAttempt.setUserAnswer(request.getUserAnswer());

        InterviewAttempt savedAttempt = interviewAttemptRepository.save(interviewAttempt);

        return mapToInterviewAttemptResponse(savedAttempt);
    }

    // Get Attempt By ID
    public InterviewAttemptResponse getAttemptById(Long id) {

        InterviewAttempt interviewAttempt = interviewAttemptRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Interview attempt not found with ID: " + id));

        return mapToInterviewAttemptResponse(interviewAttempt);
    }

    // Get Attempts By User
    public List<InterviewAttemptResponse> getAttemptsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + userId));

        return interviewAttemptRepository.findByUser(user)
                .stream()
                .map(this::mapToInterviewAttemptResponse)
                .collect(Collectors.toList());
    }

    // Get Attempts By Question
    public List<InterviewAttemptResponse> getAttemptsByQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + questionId));

        return interviewAttemptRepository.findByQuestion(question)
                .stream()
                .map(this::mapToInterviewAttemptResponse)
                .collect(Collectors.toList());
    }

    // Delete Attempt
    public void deleteAttempt(Long id) {

        InterviewAttempt interviewAttempt = interviewAttemptRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Interview attempt not found with ID: " + id));

        interviewAttemptRepository.delete(interviewAttempt);
    }

    // Helper Method
    private InterviewAttemptResponse mapToInterviewAttemptResponse(InterviewAttempt interviewAttempt) {

        return new InterviewAttemptResponse(
                interviewAttempt.getId(),
                interviewAttempt.getQuestion().getQuestion(),
                interviewAttempt.getUserAnswer(),
                interviewAttempt.getQuestion().getAnswer(),
                interviewAttempt.getAttemptedAt()
        );
    }

}