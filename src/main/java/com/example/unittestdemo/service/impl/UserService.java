package com.example.unittestdemo.service.impl;

import com.example.unittestdemo.domain.User;
import com.example.unittestdemo.repository.UserRepository;
import com.example.unittestdemo.request.UserRegisterRequest;
import com.example.unittestdemo.response.UserRegisterResponse;
import com.example.unittestdemo.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegister) {
        Optional<User> optionalUser = userRepository.findOneByUsername(userRegister.getUsername());
        UserRegisterResponse response = new UserRegisterResponse();
        if(optionalUser.isPresent()){
            response.setCode("F");
            response.setMessage("Username is present");
        }else {
            User user = new User();
            user.setUsername(userRegister.getUsername());
            user.setPassword(encoder.encode(userRegister.getPassword()));
            userRepository.save(user);

            response.setCode("S");
            response.setMessage("Register success");
        }
        return response;
    }
}
