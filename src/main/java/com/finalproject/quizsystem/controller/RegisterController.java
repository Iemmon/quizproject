package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, @Valid @ModelAttribute(name = "userRaw") UserDTO userRaw, BindingResult result) {

        if(!result.hasErrors() && !userService.isExist(userRaw.getEmail())){
            Optional<User> user = userService.register(userRaw.getEmail(), userRaw.getPassword());
            System.out.println("gfjhgfjhgf");
            if (user.isPresent()) {
                return "redirect:/login";
            }
        }
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@ModelAttribute(name = "userRaw") UserDTO userRaw) {
        return "registration";
    }
}
