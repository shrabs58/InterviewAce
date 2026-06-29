package com.interviewace.service;

import com.interviewace.dto.QuestionRequest;
import com.interviewace.dto.QuestionResponse;
import com.interviewace.entity.Category;
import com.interviewace.entity.Question;
import com.interviewace.exception.ResourceNotFoundException;
import com.interviewace.repository.CategoryRepository;
import com.interviewace.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    public QuestionService(QuestionRepository questionRepository,
                           CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    // Create Question
    public QuestionResponse createQuestion(QuestionRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with ID: " + request.getCategoryId()));

        Question question = new Question();
        question.setQuestion(request.getQuestion());
        question.setAnswer(request.getAnswer());
        question.setDifficulty(request.getDifficulty());
        question.setCategory(category);

        Question savedQuestion = questionRepository.save(question);

        return mapToResponse(savedQuestion);
    }

    // Get All Questions
    public List<QuestionResponse> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get Question By ID
    public QuestionResponse getQuestionById(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + id));

        return mapToResponse(question);
    }

    // Update Question
    public QuestionResponse updateQuestion(Long id, QuestionRequest request) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + id));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with ID: " + request.getCategoryId()));

        question.setQuestion(request.getQuestion());
        question.setAnswer(request.getAnswer());
        question.setDifficulty(request.getDifficulty());
        question.setCategory(category);

        Question updatedQuestion = questionRepository.save(question);

        return mapToResponse(updatedQuestion);
    }

    // Delete Question
    public void deleteQuestion(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + id));

        questionRepository.delete(question);
    }

    // Filter by Category
    public List<QuestionResponse> getQuestionsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with ID: " + categoryId));

        return questionRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Search Questions
    public List<QuestionResponse> searchQuestions(String keyword) {

        return questionRepository.findByQuestionContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Helper Method
    private QuestionResponse mapToResponse(Question question) {

        return new QuestionResponse(
                question.getId(),
                question.getQuestion(),
                question.getAnswer(),
                question.getDifficulty(),
                question.getCategory().getId(),
                question.getCategory().getName()
        );
    }

}