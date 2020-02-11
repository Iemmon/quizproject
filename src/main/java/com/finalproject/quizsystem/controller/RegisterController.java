package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegisterController {

    private  final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration")
    public String registration(Model model, @RequestParam(value = "email", defaultValue = "") String login, @RequestParam(value = "password", defaultValue = "") String password, @RequestParam(value = "passconf", defaultValue = "") String passconf) {

        //TODO add validation
        if (login.length() > 0) {
            Optional<User> user = userService.register(login, password);
            if (user.isPresent()) {
                return "redirect:/login";
            }
        }
        return "registration";
    }
}
