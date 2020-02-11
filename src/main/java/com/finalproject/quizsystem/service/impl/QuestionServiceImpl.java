package com.finalproject.quizsystem.service.impl;


import com.finalproject.quizsystem.repository.QuestionRepository;
import com.finalproject.quizsystem.entity.Answer;
import com.finalproject.quizsystem.entity.Question;
import com.finalproject.quizsystem.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAllByTestId(Long id) {
        return questionRepository.findAllByTestId(id);
    }

    @Override
    public List<Question> getIncorrectAnsweredQuestions(Long testId, Set<Long> answeredQuestions) {
        List<Question> incorrectUserQuestions = new ArrayList<>();
        List<Question> questions = questionRepository.findAllByTestId(testId);
        for(Question q : questions){
            boolean isCorrectQuestion = true;
            for (Answer a : q.getAnswers()){
                if(!answeredQuestions.contains(a.getId()) && a.isCorrect()){
                    isCorrectQuestion = false;
                }
                if(answeredQuestions.contains(a.getId()) && !a.isCorrect()){
                    isCorrectQuestion = false;
                }
            }
            if(!isCorrectQuestion){
                incorrectUserQuestions.add(q);
            }
        }
        return incorrectUserQuestions;
    }
}
