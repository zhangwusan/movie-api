package com.api.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.request.UserLoginRequest;
import com.api.movie.dtos.request.UserRegisterRequest;
import com.api.movie.dtos.response.UserLoginResponse;
import com.api.movie.dtos.response.UserRegisterResponse;
import com.api.movie.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Controller for user authentication and authorization
    // Implement methods for login, logout, etc.
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return authService.login(userLoginRequest);
    }

    @PostMapping("/logout")
    public String logout() {
        return authService.logout();
    }

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return authService.register(userRegisterRequest);
    }
}
