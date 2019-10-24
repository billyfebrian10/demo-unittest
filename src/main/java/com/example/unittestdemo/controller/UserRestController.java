package com.example.unittestdemo.controller;

import com.example.unittestdemo.request.UserRegisterRequest;
import com.example.unittestdemo.response.UserRegisterResponse;
import com.example.unittestdemo.service.impl.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRestController {

    private UserService userService;

    private UserRestController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody @Valid UserRegisterRequest request){
        return userService.registerUser(request);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }

}
