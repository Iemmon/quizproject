package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    boolean isExist(String email);

    Optional<User> register(String email, String password);

    Page<User> findAllUsers(Pageable pageable);
}
