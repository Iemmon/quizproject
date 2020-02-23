package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Quiz;
import com.finalproject.quizsystem.entity.Result;
import com.finalproject.quizsystem.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderImplTest {

    @Mock
    private JavaMailSender emailSender;

    private String from = "email@gmail.com";

    private MailSenderImpl mailSender;

    @Before
    public void setup(){
        mailSender = new MailSenderImpl(emailSender, from);
    }

    @Test
    public void sendResultShouldSendEmailToAddress(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("email@gmail.com");
        message.setTo("someemail@gmail.com");
        message.setSubject("Your test result");
        message.setText("Your score for the test Test1 is 100");

        Result result = new Result(1L, Quiz.builder().name("Test1").build(), User.builder().email("someemail@gmail.com").build() ,100);

        mailSender.sendResult(result);

        verify(emailSender).send(eq(message));
    }

}