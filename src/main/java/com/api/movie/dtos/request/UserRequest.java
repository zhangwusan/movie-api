package com.api.movie.dtos.request;

import com.api.movie.models.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password,
        @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email,
        Role role,
        boolean enabled) {

}
