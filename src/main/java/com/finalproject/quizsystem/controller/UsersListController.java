package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersListController {

    private final UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String viewUsersList(Model model, Pageable pageable) {
        long count = userRepository.count();
        int maxPages = (int) count / pageable.getPageSize();
        int currentPageNum = pageable.getPageNumber();
        if (currentPageNum > maxPages) {
            pageable = PageRequest.of(0, 10);
        }
        Page<User> userList = userRepository.findAll(pageable);
        model.addAttribute("users", userList);
        return "start";
    }
}
