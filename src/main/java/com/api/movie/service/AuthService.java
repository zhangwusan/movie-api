package com.api.movie.service;

import org.springframework.stereotype.Service;

import com.api.movie.dtos.request.UserLoginRequest;
import com.api.movie.dtos.request.UserRegisterRequest;
import com.api.movie.models.User;


@Service
public interface AuthService {
    public User register(UserRegisterRequest userRegisterRequest);

    public User login(UserLoginRequest userLoginRequest);

    public String logout();
}
