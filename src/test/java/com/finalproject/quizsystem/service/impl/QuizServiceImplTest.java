package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Quiz;
import com.finalproject.quizsystem.repository.QuizRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizServiceImpl testService;

    @Test
    public void findAllShouldReturnListOfQuizzes(){
        List<Quiz> tests = new ArrayList<>();

        assertEquals(tests, testService.findAllByTopicId(anyLong()));
        verify(quizRepository, atLeastOnce()).findAllByTopicId(anyLong());
    }
}
