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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    public MockMvc mvc;

    @Test
    public void registrationShouldRegisterSuccessfully() throws Exception {

        String username = "emailuser@gmail.com";
        String password = "password";
        Optional<User> user = Optional.of(User.builder().email(username).build());

        when(userService.isExist(anyString())).thenReturn(false);
        when(userService.register(anyString(), anyString())).thenReturn(user);

        mvc.perform(post("/registration")
                .param("email", username)
                .param("password", password)
                .param("passconf", password)
                .with(csrf()))
                .andExpect(status().isFound());

        verify(userService).register(eq(username), eq(password));
        verify(userService).isExist(eq(username));
    }

    @Test
    public void registrationShouldRegisterFailed() throws Exception {

        String username = "emailuser@gmail.com";
        String password = "password";
        Optional<User> user = Optional.of(User.builder().email(username).build());

        when(userService.isExist(anyString())).thenReturn(true);
        when(userService.register(anyString(), anyString())).thenReturn(user);

        mvc.perform(post("/registration")
                .param("email", username)
                .param("password", password)
                .param("passconf", password)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));

        verify(userService, never()).register(eq(username), eq(password));
        verify(userService).isExist(eq(username));
    }

    @Test
    public void registrationShouldReturnRegistrationView() throws Exception {
        mvc.perform(get("/registration"))
                .andExpect(view().name("registration"))
                .andExpect(status().isOk());
    }
}
