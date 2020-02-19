package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.Quiz;
import com.finalproject.quizsystem.entity.Result;

import java.security.Principal;

public interface MailSender {
    void sendResult(Principal user, Result result);
}
