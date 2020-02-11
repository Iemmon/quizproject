package com.finalproject.quizsystem.service;


import com.finalproject.quizsystem.entity.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    List<Question> findAllByTestId(Long id);

    List<Question> getIncorrectAnsweredQuestions(Long testId, Set<Long> answeredQuestions);
}
