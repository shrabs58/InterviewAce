package com.interviewace.repository;

import com.interviewace.entity.Category;
import com.interviewace.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(Category category);

    List<Question> findByQuestionContainingIgnoreCase(String keyword);

}