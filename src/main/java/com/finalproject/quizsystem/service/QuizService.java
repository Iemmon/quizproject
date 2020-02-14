package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> findAllByTopicId(Long id);
}
