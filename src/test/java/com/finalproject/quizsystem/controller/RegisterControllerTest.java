package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    public MockMvc mvc;

    @Test
    public void registrationShouldRegisterSuccessfully() throws Exception {
        Optional<User> user = Optional.of(new User());

        when(userService.register(anyString(), anyString())).thenReturn(user);

        mvc.perform(post("/registeration")
                .with(csrf())
                .param("email", "emailuser@gmail.com")
                .param("password", "password")
                .param("passconf", "password"))
                .andExpect(status().isOk());
    }
}
