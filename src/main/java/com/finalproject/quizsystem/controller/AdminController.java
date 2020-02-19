package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.Result;
import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.ResultService;
import com.finalproject.quizsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final ResultService resultService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String viewUsersList(Model model, Pageable pageable) {
        Page<User> page = userService.findAllUsersWithScore(pageable);
        model.addAttribute("users", page);
        return "users";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String getUserResults(Model model, Pageable pageable, @PathVariable Long userId){
        Page<Result> userresults = resultService.getAllResults(userId, pageable);
        model.addAttribute("results", userresults);
        return "userhome";
    }
}
