package com.finalproject.quizsystem.repository;

import com.finalproject.quizsystem.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByTopicId(Long topicId);
}
