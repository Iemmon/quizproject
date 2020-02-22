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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    @Test
    public void findAllShouldReturnListOfQuizzes(){
        List<Quiz> tests = new ArrayList<>();

        assertEquals(tests, quizService.findAllByTopicId(anyLong()));
        verify(quizRepository, atLeastOnce()).findAllByTopicId(anyLong());
    }

    @Test
    public void findByIdShouldReturnSingleQuiz(){
        Quiz quiz = new Quiz();
        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(quiz));

        Optional<Quiz> quiz1 = quizService.findById(1L);
        assertEquals(quiz, quiz1.get());
    }

}
