package com.interviewace.repository;

import com.interviewace.entity.InterviewAttempt;
import com.interviewace.entity.Question;
import com.interviewace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewAttemptRepository extends JpaRepository<InterviewAttempt, Long> {

    List<InterviewAttempt> findByUser(User user);

    List<InterviewAttempt> findByQuestion(Question question);

}