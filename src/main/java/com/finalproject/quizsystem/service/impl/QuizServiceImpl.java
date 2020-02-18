package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Quiz;
import com.finalproject.quizsystem.repository.QuizRepository;
import com.finalproject.quizsystem.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> findAllByTopicId(Long id) {
        return quizRepository.findAllByTopicId(id);
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }
}
