package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.UserRegisterRequest;
import com.api.movie.dtos.response.UserLoginResponse;
import com.api.movie.dtos.response.UserRegisterResponse;
import com.api.movie.models.Role;
import com.api.movie.models.User;

public class UserMapper {
    public static User mapUserByUserResquest(UserRegisterRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    public static UserLoginResponse mapUserLoginResponseByUser(User user) {
        return new UserLoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name());
    }

    public static UserRegisterResponse mapUserRegisterResponseByUser(User user) {
        return new UserRegisterResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name());
    }
}
