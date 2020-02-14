package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String viewUsersList(Model model, Pageable pageable) {
        Page<User> page = userService.findAllUsers(pageable);
        model.addAttribute("users", page);
        return "users";
    }
}
