package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.repository.UserRepository;
import com.finalproject.quizsystem.entity.Role;
import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
//import com.finalproject.quizsystem.service.validator.Validator;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final Validator<User> userValidator;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncryption/*,
                           Validator<User> userValidator*/) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncryption;
//        this.userValidator = userValidator;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException(email);
    }

    @Override
    public Optional<User> register(String email, String password) {
       //if (userValidator.validateEmail(email) && userValidator.validatePassword(password) && !userRepository.findByEmail(email).isPresent()) {
            String encryptPassword = passwordEncoder.encode(password);
            User userToSave = User.builder()
                    .email(email)
                    .password(encryptPassword)
                    .role(Role.STUDENT)
                    .build();
            userRepository.save(userToSave);
            return Optional.of(userToSave);
        //}
        //return Optional.empty();
    }
}
