package com.example.unittestdemo.service;

import com.example.unittestdemo.domain.User;
import com.example.unittestdemo.request.UserRegisterRequest;
import com.example.unittestdemo.response.UserRegisterResponse;

public interface IUserService {

    UserRegisterResponse registerUser(UserRegisterRequest user);

}
