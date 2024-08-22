package com.api.movie.service;

import org.springframework.stereotype.Service;

import com.api.movie.dtos.request.UserLoginRequest;
import com.api.movie.dtos.request.UserRegisterRequest;
import com.api.movie.dtos.response.UserLoginResponse;
import com.api.movie.dtos.response.UserRegisterResponse;

@Service
public interface AuthService {
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    public UserLoginResponse login(UserLoginRequest userLoginRequest);

    public String logout();
}
