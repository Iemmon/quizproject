package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> register(String email, String password);

   // Page<User> findAll(String page, int itemsPerPage);

}
