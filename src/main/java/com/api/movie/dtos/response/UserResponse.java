package com.api.movie.dtos.response;

import java.util.Date;
import java.util.UUID;

import com.api.movie.models.Role;

public record UserResponse(
        UUID id,
        String username,
        String password,
        String email,
        Role role,
        boolean enabled,
        Date createdAt,
        Date updatedAt) {
}
