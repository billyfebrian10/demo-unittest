package com.example.unittestdemo.service;

import com.example.unittestdemo.domain.User;
import com.example.unittestdemo.repository.UserRepository;
import com.example.unittestdemo.request.UserRegisterRequest;
import com.example.unittestdemo.response.UserRegisterResponse;
import com.example.unittestdemo.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void registerWithUserNotExist_shouldReturnSuccess(){

        UserRegisterResponse expectedResponse = new UserRegisterResponse("S", "Register success");
        UserRegisterRequest userToRegister = new UserRegisterRequest();
        userToRegister.setUsername("test");
        userToRegister.setPassword("test");

        when(userRepository.findOneByUsername(anyString())).thenReturn(Optional.empty());

        UserRegisterResponse actualResponse = userService.registerUser(userToRegister);

        assertEquals(expectedResponse.getCode(), actualResponse.getCode());
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
    }

    @Test
    public void registerWithUserExist_shouldReturnUsernameExists(){
        UserRegisterResponse expectedResponse = new UserRegisterResponse("F", "Username is present");
        UserRegisterRequest userToRegister = new UserRegisterRequest();
        userToRegister.setUsername("test");
        userToRegister.setPassword("test");

        when(userRepository.findOneByUsername(anyString())).thenReturn(Optional.of(new User()));

        UserRegisterResponse actualResponse = userService.registerUser(userToRegister);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getCode(), actualResponse.getCode());
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
    }
}
