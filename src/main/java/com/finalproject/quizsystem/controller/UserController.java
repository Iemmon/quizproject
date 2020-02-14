package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.*;
import com.finalproject.quizsystem.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    private final ResultService resultService;
    private final UserService userService;
    private final TopicService topicService;
    private final QuizService quizService;
    private final QuestionService questionService;

    @RequestMapping(value = "/home")
    public String getAllResults(Model model, Principal user){
        String email = user.getName();
        User u = (User) userService.loadUserByUsername(email);

        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");

        List<Result> resultList = resultService.getAllResults(u.getId());
        model.addAttribute("results", resultList);
        return "userhome";
    }

    @RequestMapping(value = "/topics")
    public String chooseTopic(Model model){
        List<Topic> topicList = topicService.findAll();
        model.addAttribute("topics", topicList);
        return "topics";
    }

    @RequestMapping(value = "/quizes", params = {"topic_id"})
    public String chooseQuiz(Model model, @RequestParam(value = "topic_id") Long topicId){

        List<Quiz> quizList = quizService.findAllByTopicId(topicId);
        model.addAttribute("quizes", quizList);
        return "quizes";
    }

    @RequestMapping(value = "/questions", params = {"quiz_id"})
    public String startQuiz(Model model, @RequestParam(value = "quiz_id") Long quizId){
        List<Question> questionList = questionService.findAllByTestId(quizId);
        model.addAttribute("questions", questionList);
        return "questions";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String getResult(Model model, @RequestParam(value = "result") Long[] result, @RequestParam(value = "quiz_id") Long quizId){
        Set<Long> selected = new HashSet<>(Arrays.asList(result));
        List<Question> checkedQuestions = questionService.getIncorrectAnsweredQuestions(quizId, selected);
        model.addAttribute("questions", checkedQuestions);
        model.addAttribute("selected", selected);
        return "result";
    }
}
