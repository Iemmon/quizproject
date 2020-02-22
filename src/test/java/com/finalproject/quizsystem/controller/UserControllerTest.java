package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.*;
import com.finalproject.quizsystem.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    private ResultService resultService;

    @MockBean
    private UserService userService;

    @MockBean
    private TopicService topicService;

    @MockBean
    private QuizService quizService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private MailSender mailSender;

    @Test
    @WithMockUser(roles = "STUDENT")
    public void getResultsShouldRedirectToResultsPage() throws Exception {

        User user = mock(User.class);
        when(userService.loadUserByUsername(anyString())).thenReturn(user);

        Page<Result> userResults = mock(Page.class);
        when(resultService.getAllResults(anyLong(), any(Pageable.class))).thenReturn(userResults);

        mvc.perform(get("/user/results"))
                .andExpect(status().isOk())
                .andExpect(view().name("userresults"))
                .andExpect(model().attribute("results", userResults));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void chooseTopicShouldRedirectToTopicsPage() throws Exception {
        List<Topic> topics = new ArrayList<>();
        when(topicService.findAll()).thenReturn(topics);

        mvc.perform(get("/user/topics"))
                .andExpect(status().isOk())
                .andExpect(view().name("topics"))
                .andExpect(model().attribute("topics", topics));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void chooseQuizShouldRedirectToQuizesPage() throws Exception {
        List<Quiz> quizList = new ArrayList<>();
        when(quizService.findAllByTopicId(anyLong())).thenReturn(quizList);

        mvc.perform(get("/user/quizes").param("topic_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("quizes"))
                .andExpect(model().attribute("quizes", quizList));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void startQuizShouldRedirectToQuestionPage() throws Exception {
        List<Question> questionList = new ArrayList<>();
        when(questionService.findAllByTestId(anyLong())).thenReturn(questionList);

        mvc.perform(get("/user/questions").param("quiz_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("questions"))
                .andExpect(model().attribute("questions", questionList));
    }


    @Test
    @WithMockUser(roles = "STUDENT")
    public void getResultShouldRedirectToResultPage() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        Set<Long> selected = new HashSet<>();
        List<Question> checkedQuestions = new ArrayList<>();
        when(questionService.getIncorrectAnsweredQuestions(anyLong(), anySet())).thenReturn(checkedQuestions);

        mvc.perform(post("/user/result"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("questions", checkedQuestions))
                .andExpect(model().attribute("selected", selected))
                .andExpect(content().contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }
}
