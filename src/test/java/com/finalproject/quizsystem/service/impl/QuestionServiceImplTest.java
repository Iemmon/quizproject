package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Answer;
import com.finalproject.quizsystem.entity.Question;
import com.finalproject.quizsystem.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    public QuestionRepository questionRepository;

    @InjectMocks
    public QuestionServiceImpl questionService;

    @Test
    public void findAllByTestIdShouldReturnListOfQuestions() {
        Long id = 1L;
        List<Question> listOfQuestions = new ArrayList<>();
        when(questionRepository.findAllByQuizId(eq(id))).thenReturn(listOfQuestions);

        List<Question> questions = questionService.findAllByTestId(id);

        assertEquals(listOfQuestions, questions);
    }

    @Test
    public void getIncorrectAnsweredQuestionsShouldCheckIfAnswersCorrect() {
        List<Question> questionList = initialiseList();

        when(questionRepository.findAllByQuizId(any(Long.class))).thenReturn(questionList);

        Set<Long> userAnswers = new HashSet<>();
        userAnswers.add(1L);
        assertEquals(0, questionService.getIncorrectAnsweredQuestions(10L, userAnswers).size());
    }

    private List<Question> initialiseList(){
        List<Question> questionList = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = Answer.builder()
                .id(1L)
                .answerOption("Answer")
                .isCorrect(true)
                .build();

        Answer answer2 = Answer.builder()
                .id(2L)
                .answerOption("Other answer")
                .isCorrect(false)
                .build();

        Answer answer3 = Answer.builder()
                .id(3L)
                .answerOption("Other answer")
                .isCorrect(false)
                .build();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        Question question = Question.builder()
                .id(1L)
                .question("Question text")
                .answers(answers)
                .build();
        questionList.add(question);
        return questionList;
    }
}