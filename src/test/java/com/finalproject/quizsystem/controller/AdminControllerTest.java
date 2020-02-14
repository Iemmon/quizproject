package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    public UserService userService;

    @Autowired
    public AdminController adminController;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void viewUsersListShouldReturnString() throws Exception {

        Page<User> page = mock(Page.class);
        when(userService.findAllUsers(any(Pageable.class))).thenReturn(page);

        mvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", page));
    }
}