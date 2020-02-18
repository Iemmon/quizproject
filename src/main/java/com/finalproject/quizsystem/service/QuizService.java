package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.Quiz;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<Quiz> findAllByTopicId(Long id);

    Optional<Quiz> findById(Long id);
}
