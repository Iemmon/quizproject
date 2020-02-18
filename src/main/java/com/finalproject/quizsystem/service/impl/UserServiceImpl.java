package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Role;
import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.service.UserService;
import com.finalproject.quizsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findOneByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException(email);
    }

    @Override
    public boolean isExist(String email) {
        try{
            this.loadUserByUsername(email);
            return true;
        } catch (UsernameNotFoundException e){
            return false;
        }
    }

    @Override
    public Optional<User> register(String email, String password) {
        String encryptPassword = passwordEncoder.encode(password);
        User userToSave = User.builder()
                .email(email)
                .password(encryptPassword)
                .role(Role.STUDENT)
                .build();
        User user = userRepository.save(userToSave);
        System.out.println(user);
        return Optional.of(user);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        long count = userRepository.count();
        int maxPages = (int) count / pageable.getPageSize();
        int currentPageNum = pageable.getPageNumber();
        if (currentPageNum > maxPages) {
            pageable = PageRequest.of(0, 5);
        }
        return userRepository.findAll(pageable);
    }
}
