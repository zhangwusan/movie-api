package com.api.movie.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.dtos.mapping.UserMapper;
import com.api.movie.dtos.request.UserLoginRequest;
import com.api.movie.dtos.request.UserRegisterRequest;
import com.api.movie.models.User;
import com.api.movie.service.AuthService;
import com.api.movie.service.UserService;

@Service
public class AuthServiceImplement implements AuthService {

    @Autowired
    private UserService userService;

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userService.getUserByEmail(userLoginRequest.getEmail());
        if (!userService.isValidPassword(userLoginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        // return uer details, jwt token
        return user;
    }

    @Override
    public String logout() {
        // Clear the JWT token from the user's session
        return "User logged out successfully";
    }

    @Override
    public User register(UserRegisterRequest userRegisterRequest) {
        if (userService.isValidEmail(userRegisterRequest.getEmail())) {
            throw new RuntimeException("User already exists with this email.");
        }
        User registerUser = UserMapper.mapUserByUserResquest(userRegisterRequest);
        return userService.createUser(registerUser);
    }

}