package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Role;
import com.finalproject.quizsystem.entity.User;
import com.finalproject.quizsystem.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String EMAIL = "email@gmail.com";
    private static final String PASS = "password";
    private static final String ENCRYPTED_PASS = "$2a$10$KdZn8p.eNGvWN4ReGEcRJeIVes44VCdqZgViOH1wsVCpSZ/g3jOKu";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private Pageable pageable;

    @Test
    public void loadUserByUsernameShouldReturnUser() {
        User user = User.builder().email(EMAIL).build();

        when(userRepository
                .findOneByEmail(eq(EMAIL)))
                .thenReturn(Optional.of(user));
        UserDetails userDetails = userService.loadUserByUsername(EMAIL);
        assertEquals(EMAIL, userDetails.getUsername());
    }

    @Test
    public void registerShouldSaveUser() {
        User user = User.builder().email(EMAIL).password(ENCRYPTED_PASS).role(Role.STUDENT).build();

        when(passwordEncoder.encode(eq(PASS))).thenReturn(ENCRYPTED_PASS);
        when(userRepository.save(eq(user))).thenReturn(user);

        Optional<User> savedUser = userService.register(EMAIL, PASS);

        assertEquals(user, savedUser.get());

        verify(passwordEncoder).encode(PASS);
        verify(userRepository).save(user);
    }

    @Test
    public void isExistShouldReturnTrueWhenUserExists(){
        String email = "email@gmail.com";
        when(userRepository.findOneByEmail(anyString())).thenReturn(Optional.of(User.builder().build()));

        assertTrue(userService.isExist(email));
        verify(userRepository).findOneByEmail(eq(email));
    }

    @Test
    public void isExistShouldReturnFalseWhenUserDoesntExist(){
        String email = "email@gmail.com";
        when(userRepository.findOneByEmail(anyString())).thenReturn(Optional.empty());

        assertFalse(userService.isExist(email));
        verify(userRepository).findOneByEmail(eq(email));
    }

    @Test
    public void findAllUsersWithScoreShouldReturnPage(){
        long count = 10L;

        Page<User> users = mock(Page.class);
        when(userRepository.count()).thenReturn(count);
        when(pageable.getPageSize()).thenReturn(10);

        when(userRepository.findAllUsersWithScore(pageable)).thenReturn(users);

        Page<User> userResults = userService.findAllUsersWithScore(pageable);
        assertEquals(users, userResults);
    }

}