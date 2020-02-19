package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Result;
import com.finalproject.quizsystem.service.MailSender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class MailSenderImpl implements MailSender {

    private final JavaMailSender emailSender;
    private final String from;

    MailSenderImpl(JavaMailSender emailSender, @Value("${spring.mail.username}") String from){
        this.emailSender = emailSender;
        this.from = from;
    }

    @Override
    public void sendResult(Principal user, Result result) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getName());
        message.setSubject("Your test result");
        message.setText(String.format("Your score for the test %s is %d", result.getQuiz().getName(), result.getScore()));
        emailSender.send(message);
    }
}
