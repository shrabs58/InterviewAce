package com.interviewace.service;

import com.interviewace.dto.QuestionRequest;
import com.interviewace.dto.QuestionResponse;
import com.interviewace.entity.Category;
import com.interviewace.entity.Question;
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

    public QuestionResponse createQuestion(QuestionRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Question question = new Question();
        question.setQuestion(request.getQuestion());
        question.setAnswer(request.getAnswer());
        question.setDifficulty(request.getDifficulty());
        question.setCategory(category);

        Question savedQuestion = questionRepository.save(question);

        return new QuestionResponse(
                savedQuestion.getId(),
                savedQuestion.getQuestion(),
                savedQuestion.getAnswer(),
                savedQuestion.getDifficulty(),
                savedQuestion.getCategory().getId(),
                savedQuestion.getCategory().getName()
        );
    }

    public List<QuestionResponse> getAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(question -> new QuestionResponse(
                        question.getId(),
                        question.getQuestion(),
                        question.getAnswer(),
                        question.getDifficulty(),
                        question.getCategory().getId(),
                        question.getCategory().getName()
                ))
                .collect(Collectors.toList());
    }
}