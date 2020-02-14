package com.finalproject.quizsystem.controller;

import com.finalproject.quizsystem.config.PassConf;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@PassConf
public class UserDTO {

    @Email
    String email;

    String password;
    String passconf;
}
