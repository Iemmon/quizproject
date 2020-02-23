package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.*;
import com.finalproject.quizsystem.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

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
    private final MailSender mailSender;

    @RequestMapping(value = "/results")
    public String getAllResults(Model model, Principal user, Pageable pageable) {
        String email = user.getName();
        User u = (User) userService.loadUserByUsername(email);

        Page<Result> resultList = resultService.getAllResults(u.getId(), pageable);
        model.addAttribute("results", resultList);

        return "userresults";
    }

    @RequestMapping(value = "/topics")
    public String chooseTopic(Model model) {
        List<Topic> topicList = topicService.findAll();
        model.addAttribute("topics", topicList);
        return "topics";
    }

    @RequestMapping(value = "/quizes", params = {"topic_id"})
    public String chooseQuiz(Model model, @RequestParam(value = "topic_id") Long topicId) {

        List<Quiz> quizList = quizService.findAllByTopicId(topicId);
        model.addAttribute("quizes", quizList);
        return "quizes";
    }

    @RequestMapping(value = "/questions", params = {"quiz_id"})
    public String startQuiz(Model model, @RequestParam(value = "quiz_id") Long quizId) {
        List<Question> questionList = questionService.findAllByTestId(quizId);
        model.addAttribute("questions", questionList);
        return "questions";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST, params = {"result", "quiz_id"})
    public String getResult(Model model, @RequestParam(value = "result") Long[] results, @RequestParam(value = "quiz_id") Long quizId, Principal user) {
        Set<Long> selected = new HashSet<>(Arrays.asList(results));
        List<Question> incorrectQuestions = questionService.getIncorrectAnsweredQuestions(quizId, selected);
        model.addAttribute("questions", incorrectQuestions);
        model.addAttribute("selected", selected);

        Optional<Quiz> quiz = quizService.findById(quizId);
        if (quiz.isPresent()) {

            Integer score = (int)(100 * (double)(quiz.get().getQuestions().size() - incorrectQuestions.size())/quiz.get().getQuestions().size());
            model.addAttribute("score", score);

            String email = user.getName();
            User u = (User) userService.loadUserByUsername(email);
            Result result = Result.builder().score(score).user(u).quiz(quiz.get()).build();
            resultService.saveResult(result);

            mailSender.sendResult(result);
            log.info("Mail sender call occurred");

            return "result";
        } else {
            throw new NoSuchElementException();
        }
    }
}
