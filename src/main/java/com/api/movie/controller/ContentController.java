package com.api.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.mapping.UserMapper;
import com.api.movie.dtos.request.UserLoginRequest;
import com.api.movie.models.User;
import com.api.movie.service.UserService;

@RestController
public class ContentController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String indexPage() {
        return "hello to index page";
    }

    @PostMapping("/login")
    public String loginPage(@RequestBody UserLoginRequest userLoginRequest) {
        User user = userService.getUserByEmail(userLoginRequest.getEmail());
        userLoginRequest.setUsername(user.getUsername());
        return userService.verify(UserMapper.mapUserLoginRequestToUser(userLoginRequest));
    }
}
